package me.renzy.protocol.network.packet.subscription;

import lombok.NonNull;
import me.renzy.protocol.network.packet.Packet;
import me.renzy.protocol.network.packet.Packets;
import me.renzy.protocol.auth.PacketAuthenticator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

final class PacketSubscriptionBuilderImpl<T extends Packet> implements PacketSubscriptionBuilder<T> {

    final Class<T> packetClass;

    final List<BiPredicate<PacketSubscription<T>, T>> filters = new ArrayList<>();
    final List<BiPredicate<PacketSubscription<T>, T>> preExpires = new ArrayList<>();
    final List<BiPredicate<PacketSubscription<T>, T>> filterExpires = new ArrayList<>();
    final List<BiPredicate<PacketSubscription<T>, T>> handleExpires = new ArrayList<>();

    Consumer<? super T> handler;
    BiConsumer<? super T, Throwable> exceptionConsumer;

    PacketSubscriptionBuilderImpl(Class<T> packetClass) {
        this.packetClass = packetClass;
    }

    @Override
    public @NonNull PacketSubscriptionBuilder<T> expiryIf(@NonNull BiPredicate<PacketSubscription<T>, T> predicate, @NonNull ExpiryTestStage... points) {
        for (ExpiryTestStage point : points) {
            switch (point) {
                case PRE:
                    this.preExpires.add(predicate);
                    break;
                case FILTER:
                    this.filterExpires.add(predicate);
                    break;
                case HANDLE:
                    this.handleExpires.add(predicate);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        return this;
    }

    @Override
    public @NonNull PacketSubscriptionBuilder<T> filter(@NonNull BiPredicate<PacketSubscription<T>, T> predicate) {
        this.filters.add(predicate);
        return this;
    }

    @Override
    public @NonNull PacketSubscriptionBuilder<T> exceptionConsumer(@NonNull BiConsumer<? super T, Throwable> consumer) {
        this.exceptionConsumer = consumer;
        return this;
    }

    @Override
    public @NonNull PacketSubscription<T> handler(@NonNull Consumer<? super T> consumer) {
        this.handler = consumer;
        PacketSubscriptionImpl<T> subscription = new PacketSubscriptionImpl<>(this);
        PacketAuthenticator authenticator = getAuthenticator();
        authenticator.subscribe(subscription);
        return subscription;
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
}
