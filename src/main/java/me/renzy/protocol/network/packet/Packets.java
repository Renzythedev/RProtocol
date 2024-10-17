package me.renzy.protocol.network.packet;

import com.google.common.base.Preconditions;
import io.netty.channel.Channel;
import lombok.NonNull;
import me.renzy.protocol.auth.PacketAuthenticator;
import me.renzy.protocol.network.packet.subscription.PacketSubscription;
import me.renzy.protocol.network.packet.subscription.PacketSubscriptionBuilder;
import me.renzy.protocol.utils.AsyncExecutor;

import java.util.Map;
import java.util.Set;

public final class Packets {

    private static final PacketAuthenticator authenticator = PacketAuthenticator.newAuthenticator();

    @NonNull
    public static <T extends Packet> PacketSubscriptionBuilder<T> subscribe(@NonNull Class<T> packetClass) {
        return PacketSubscriptionBuilder.newBuilder(packetClass);
    }

    public static void register(int id, @NonNull Class<? extends Packet> packetClass) {
        authenticator.register(id,packetClass);
    }

    public static void register(@NonNull Map<Integer,@NonNull Class<? extends Packet>> packets){
        for (Map.Entry<Integer,Class<? extends Packet>> entry : packets.entrySet()) {
            authenticator.register(entry.getKey(),entry.getValue());
        }
    }

    public static void unregister(int id) {
        authenticator.unregister(id);
    }

    public static void unregister(@NonNull Class<? extends Packet> packetClass) {
        authenticator.unregister(packetClass);
    }

    public static boolean isAuthed(@NonNull Class<? extends Packet> packetClass) {
        return authenticator.isAuthed(packetClass);
    }

    public static boolean isIdTaken(int id) {
        return authenticator.isIdTaken(id);
    }

    public static int getId(@NonNull Class<? extends Packet> packetClass) {
        return authenticator.getId(packetClass);
    }

    @NonNull
    public static Class<? extends Packet> getPacketClass(int id) {
        return authenticator.getPacketClass(id);
    }

    public static Set<Class<? extends Packet>> getPacketClasses() {
        return (Set<Class<? extends Packet>>) authenticator.authedClasses();
    }

    public static Set<Integer> getTakenIds() {
        return (Set<Integer>) authenticator.takenIds();
    }

    public static Set<PacketSubscription<? extends Packet>> subscriptionsOf(@NonNull Class<? extends Packet> packetClass) {
        return (Set<PacketSubscription<? extends Packet>>) authenticator.subscriptions(packetClass);
    }

    public static void sendPacketAsync(@NonNull Channel channel, @NonNull Packet packet) {
        AsyncExecutor.execute(() -> sendPacketSync(channel,packet));
    }

    public static void sendPacketSync(@NonNull Channel channel, @NonNull Packet packet) {
        Preconditions.checkNotNull(packet, packet.getClass().getSimpleName() + " cannot be null.");
        Preconditions.checkNotNull(channel, "channel cannot be null.");
        channel.writeAndFlush(packet);
    }

    public static void callPacketAsync(@NonNull Packet packet) {
        AsyncExecutor.execute(() -> callPacketSync(packet));
    }

    public static void callPacketSync(@NonNull Packet packet) {
        Preconditions.checkNotNull(packet, packet.getClass().getSimpleName() + " cannot be null.");
        Set<PacketSubscription<? extends Packet>> subscriptions = subscriptionsOf(packet.getClass());
        subscriptions.forEach(subscription -> subscription.getHandler().consume(packet));
    }

    private Packets() {}
}
