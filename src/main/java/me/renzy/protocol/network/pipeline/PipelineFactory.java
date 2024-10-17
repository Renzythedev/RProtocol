package me.renzy.protocol.network.pipeline;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import lombok.NonNull;

public interface PipelineFactory<T extends Channel> {

    @NonNull
    ChannelInitializer<T> create();
}
