package me.renzy.protocol.auth;

import lombok.Getter;
import me.renzy.protocol.network.packet.Packet;
import me.renzy.protocol.network.packet.subscription.PacketSubscription;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
final class AuthedPacket {

    private final int id;
    private final Class<? extends Packet> packetClass;
    private final Set<PacketSubscription<? extends Packet>> subscriptions;

    AuthedPacket(int id, Class<? extends Packet> packetClass) {
        List<Constructor<?>> EMPTY_CONSTRUCTORS = Arrays.stream(packetClass.getConstructors()).filter(constructor -> constructor.getParameterCount() == 0).toList();
        if (EMPTY_CONSTRUCTORS.isEmpty())
            throw new RuntimeException("Cannot found no-arg-constructor in packet class");

        this.packetClass = packetClass;
        this.id = id;
        this.subscriptions = new HashSet<>();
    }
}
