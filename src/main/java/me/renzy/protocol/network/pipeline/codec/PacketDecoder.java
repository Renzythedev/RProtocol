package me.renzy.protocol.network.pipeline.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.renzy.protocol.network.packet.Packet;
import me.renzy.protocol.network.packet.Packets;
import me.renzy.protocol.serializer.PacketDataDeserializer;
import me.renzy.protocol.serializer.types.PacketData;

import java.util.List;

public final class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext c, ByteBuf byteBuf, List<Object> list) throws Exception {
        PacketDataDeserializer deserializer = PacketData.deserializer(byteBuf);
        int id = deserializer.integers().read();

        Class<? extends Packet> packetClass = Packets.getPacketClass(id);

        Packet packet = packetClass.getConstructor().newInstance();
        packet.read(deserializer);
    }
}
