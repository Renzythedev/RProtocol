package me.renzy.protocol.network.packet;

import lombok.NonNull;
import me.renzy.protocol.auth.PacketAuthenticator;
import me.renzy.protocol.network.packet.subscription.PacketSubscription;
import me.renzy.protocol.network.packet.subscription.PacketSubscriptionBuilder;

import java.util.Collection;
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

    public static void callPacket(@NonNull Packet packet) {
        Set<PacketSubscription<? extends Packet>> subscriptions = subscriptionsOf(packet.getClass());

        subscriptions.forEach(subscription -> subscription.getHandler().consume(packet));
    }

    private Packets() {}
}
