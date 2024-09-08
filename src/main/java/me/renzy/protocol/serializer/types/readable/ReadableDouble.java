package me.renzy.protocol.serializer.types.readable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class ReadableDouble extends AbstractReadable<Double>{
    public ReadableDouble(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public Double read() {
        return this.byteBuf.readDouble();
    }
}
