package me.renzy.protocol.network.pipeline.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.renzy.protocol.network.packet.Packet;
import me.renzy.protocol.network.packet.Packets;
import me.renzy.protocol.serializer.PacketDataSerializer;
import me.renzy.protocol.serializer.types.PacketData;

public final class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext c, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketDataSerializer serializer = PacketData.serializer(byteBuf);
        serializer.integers().write(Packets.getId(packet.getClass()));
        packet.write(serializer);
    }
}
