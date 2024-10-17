package me.renzy.protocol.serializer.types.readable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class ReadableByte extends AbstractReadable<Byte> {
    public ReadableByte(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public Byte read() {
        return this.byteBuf.readByte();
    }
}
