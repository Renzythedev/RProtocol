package me.renzy.protocol.network.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import me.renzy.protocol.network.packet.Packet;
import me.renzy.protocol.network.packet.Packets;

public class ChannelReader extends SimpleChannelInboundHandler<Packet> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        Packets.callPacket(packet);
    }
}
