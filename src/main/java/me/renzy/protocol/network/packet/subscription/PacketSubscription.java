package me.renzy.protocol.network.packet.subscription;

import lombok.NonNull;
import me.renzy.protocol.network.packet.Packet;
import me.renzy.protocol.network.handler.PacketHandler;

public interface PacketSubscription<T extends Packet> {

    @NonNull
    Class<T> getPacketClass();

    int getCallCounter();

    int getId();

    @NonNull
    PacketHandler<T> getHandler();

    boolean isActive();

    void unregister();
}
