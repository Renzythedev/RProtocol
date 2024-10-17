package me.renzy.protocol.auth;

import lombok.Getter;
import lombok.NonNull;
import me.renzy.protocol.network.packet.Packet;
import me.renzy.protocol.network.packet.subscription.PacketSubscription;

import java.lang.reflect.Constructor;
import java.util.*;

final class PacketAuthenticatorImpl implements PacketAuthenticator{

    private final Set<AuthedPacket> AUTHENTICATED = new HashSet<>();


    @Override
    public void register(int id, @NonNull Class<? extends Packet> packetClass) {
        if (isIdTaken(id) || isAuthed(packetClass))
            return;

        this.AUTHENTICATED.add(new AuthedPacket(id,packetClass));
    }

    @Override
    public void unregister(@NonNull Class<? extends Packet> packetClass) {
        this.AUTHENTICATED.remove(getAuthedPacket(packetClass));
    }

    @Override
    public void unregister(int id) {
        this.AUTHENTICATED.remove(getAuthedPacket(id));
    }

    @Override
    public void subscribe(@NonNull PacketSubscription<? extends Packet> subscription) {
        AuthedPacket authedPacket = getAuthedPacket(subscription.getPacketClass());
        authedPacket.getSubscriptions().add(subscription);
    }

    @Override
    public void unsubscribe(@NonNull PacketSubscription<? extends Packet> subscription) {
        AuthedPacket authedPacket = getAuthedPacket(subscription.getPacketClass());
        authedPacket.getSubscriptions().remove(subscription);
    }

    @Override
    public boolean isAuthed(@NonNull Class<? extends Packet> packetClass) {
        return this.authedClasses().stream().anyMatch(clazz ->clazz.equals(packetClass));
    }

    @Override
    public boolean isIdTaken(int id) {
        return this.AUTHENTICATED.stream().anyMatch(authedPacket -> authedPacket.getId() == id);
    }

    @Override
    public int getId(@NonNull Class<? extends Packet> packetClass) {
        return getAuthedPacket(packetClass).getId();
    }

    @Override
    public @NonNull Class<? extends Packet> getPacketClass(int id) {
        return getAuthedPacket(id).getPacketClass();
    }

    @Override
    public @NonNull Collection<Class<? extends Packet>> authedClasses() {
        Set<Class<? extends Packet>> classes = new HashSet<>();

        this.AUTHENTICATED.forEach(authedPacket -> classes.add(authedPacket.getPacketClass()));

        return classes;
    }

    @Override
    public @NonNull Collection<Integer> takenIds() {
        Set<Integer> ids = new HashSet<>();
        this.AUTHENTICATED.forEach(authedPacket -> ids.add(authedPacket.getId()));
        return ids;
    }

    @Override
    public @NonNull Collection<PacketSubscription<? extends Packet>> subscriptions(@NonNull Class<? extends Packet> packetClass) {
        return getAuthedPacket(packetClass).getSubscriptions();
    }

    private AuthedPacket getAuthedPacket(Class<? extends Packet> packetClass) {
        return this.AUTHENTICATED
                .stream()
                .filter(authedPacket -> authedPacket.getPacketClass().equals(packetClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot found the " + packetClass.getSimpleName() + " packet in authed packets."));
    }

    private AuthedPacket getAuthedPacket(int id) {
        return this.AUTHENTICATED
                .stream()
                .filter(authedPacket -> authedPacket.getId()== id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot found packet with" + id + "id in authed packets."));
    }
}
