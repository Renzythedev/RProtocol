package me.renzy.protocol.serializer.types.readable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class ReadableFloat extends AbstractReadable<Float> {
    public ReadableFloat(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public Float read() {
        return this.byteBuf.readFloat();
    }
}
