package me.renzy.protocol.network.handler;

import lombok.NonNull;
import me.renzy.protocol.network.packet.Packet;

public interface PacketConsumer {

    void consume(@NonNull Packet packet);
}
