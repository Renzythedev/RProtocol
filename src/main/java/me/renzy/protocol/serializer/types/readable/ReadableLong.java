package me.renzy.protocol.serializer.types.readable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class ReadableLong extends AbstractReadable<Long> {
    public ReadableLong(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public Long read() {
        return this.byteBuf.readLong();
    }
}
