package me.renzy.protocol.network.packet.subscription;

import lombok.NonNull;
import me.renzy.protocol.network.packet.Packet;
import me.renzy.protocol.network.packet.Packets;
import me.renzy.protocol.auth.PacketAuthenticator;
import me.renzy.protocol.network.handler.PacketHandler;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

final class PacketSubscriptionImpl<T extends Packet> implements PacketSubscription<T>, PacketHandler<T> {

    private final Class<T> packetClass;

    private final BiPredicate<PacketSubscription<T>, T>[] filters;
    private final BiPredicate<PacketSubscription<T>, T>[] preExpires;
    private final BiPredicate<PacketSubscription<T>, T>[] filterExpires;
    private final BiPredicate<PacketSubscription<T>, T>[] handleExpires;

    private final Consumer<? super T> handler;
    private final BiConsumer<? super T, Throwable> exceptionConsumer;

    private final AtomicInteger callCounter = new AtomicInteger(0);
    private final AtomicBoolean active = new AtomicBoolean(true);

    PacketSubscriptionImpl(PacketSubscriptionBuilderImpl<T> builder) {
        this.packetClass = builder.packetClass;
        this.handler= builder.handler;
        this.exceptionConsumer = builder.exceptionConsumer;
        this.filters = builder.filters.toArray(new BiPredicate[builder.filters.size()]);
        this.preExpires = builder.preExpires.toArray(new BiPredicate[builder.preExpires.size()]);
        this.filterExpires = builder.filterExpires.toArray(new BiPredicate[builder.filterExpires.size()]);
        this.handleExpires = builder.handleExpires.toArray(new BiPredicate[builder.handleExpires.size()]);
    }


    @Override
    public void consume(@NonNull Packet packet) {
        T instance = this.packetClass.cast(packet);

        for (BiPredicate<PacketSubscription<T>, T> test : this.preExpires) {
            if (test.test(this,instance)) {
                unregister();
                return;
            }
        }
        try {
            for (BiPredicate<PacketSubscription<T>, T> test : this.filters) {
                if (!test.test(this,instance))
                    return;
            }

            for (BiPredicate<PacketSubscription<T>, T> test : this.filterExpires) {
                if (test.test(this,instance)) {
                    unregister();
                    return;
                }
            }

            this.handler.accept(instance);
            this.callCounter.incrementAndGet();
        }catch (Throwable t) {
            this.exceptionConsumer.accept(instance,t);
        }
    }

    @Override
    public @NonNull Class<T> getPacketClass() {
        return this.packetClass;
    }

    @Override
    public int getCallCounter() {
        return this.callCounter.get();
    }

    @Override
    public int getId() {
        return Packets.getId(this.packetClass);
    }

    @Override
    public @NonNull PacketHandler<T> getHandler() {
        return this;
    }

    @Override
    public boolean isActive() {
        return this.active.get();
    }

    @Override
    public void unregister() {
        if (!this.active.compareAndSet(false,true)) {
            throw new RuntimeException("This subscription already unregistered.");
        }

        PacketAuthenticator authenticator = getAuthenticator();
        authenticator.unsubscribe(this);
    }

    public static PacketAuthenticator getAuthenticator() {
        try {
            Field field = Packets.class.getDeclaredField("authenticator");
            field.setAccessible(true);
            return (PacketAuthenticator) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @NonNull Consumer<? super T> getConsumer() {
        return this.handler;
    }
}
