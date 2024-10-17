package me.renzy.protocol.auth;

import lombok.NonNull;
import me.renzy.protocol.network.packet.Packet;
import me.renzy.protocol.network.packet.subscription.PacketSubscription;

import java.util.Collection;

public interface PacketAuthenticator {

    static PacketAuthenticator newAuthenticator() { return new PacketAuthenticatorImpl(); }

    void register(int id,@NonNull Class<? extends Packet> packetClass);

    void unregister(@NonNull Class<? extends Packet> packetClass);

    void unregister(int id);

    void subscribe(@NonNull PacketSubscription<? extends Packet> subscription);

    void unsubscribe(@NonNull PacketSubscription<? extends Packet> subscription);

    boolean isAuthed(@NonNull Class<? extends Packet> packetClass);

    boolean isIdTaken(int id);

    int getId(@NonNull Class<? extends Packet> packetClass);

    @NonNull
    Class<? extends Packet> getPacketClass(int id);

    @NonNull
    Collection<Class<? extends Packet>> authedClasses();

    @NonNull
    Collection<Integer> takenIds();

    @NonNull
    Collection<PacketSubscription<? extends Packet>> subscriptions(@NonNull Class<? extends Packet> packetClass);
}
