package me.renzy.protocol.network.handler;

import lombok.NonNull;
import me.renzy.protocol.network.packet.Packet;

import java.util.function.Consumer;

public interface PacketHandler<T extends Packet> extends PacketConsumer{


    @NonNull
    Consumer<? super T> getConsumer();
}
