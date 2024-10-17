package me.renzy.protocol.serializer.types.readable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class ReadableInt extends AbstractReadable<Integer> {

    public ReadableInt(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public Integer read() {
        return this.byteBuf.readInt();
    }
}
