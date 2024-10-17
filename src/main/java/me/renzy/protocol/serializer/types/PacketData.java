package me.renzy.protocol.serializer.types;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import me.renzy.protocol.serializer.PacketDataDeserializer;
import me.renzy.protocol.serializer.PacketDataSerializer;

import java.util.Objects;

public final class PacketData {

    @NonNull
    public static PacketDataSerializer serializer(@NonNull ByteBuf byteBuf) {
        Objects.requireNonNull(byteBuf,"byteBuf");
        return new PacketDataSerializerImpl(byteBuf);
    }

    @NonNull
    public static PacketDataDeserializer deserializer(@NonNull ByteBuf byteBuf) {
        Objects.requireNonNull(byteBuf,"byteBuf");
        return new PacketDataDeserializerImpl(byteBuf);
    }

    private PacketData(){}
}
