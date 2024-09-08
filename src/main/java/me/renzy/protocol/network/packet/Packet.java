package me.renzy.protocol.network.packet;

import lombok.NonNull;
import me.renzy.protocol.serializer.PacketDataDeserializer;
import me.renzy.protocol.serializer.PacketDataSerializer;

public abstract class Packet {

    public abstract void write(@NonNull PacketDataSerializer data);

    public abstract void read(@NonNull PacketDataDeserializer data);

}
