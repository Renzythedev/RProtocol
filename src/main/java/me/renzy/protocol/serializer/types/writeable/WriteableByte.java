package me.renzy.protocol.serializer.types.writeable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class WriteableByte extends AbstractWriteable<Byte>{
    public WriteableByte(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public void write(Byte aByte) {
        this.byteBuf.writeByte(aByte);
    }
}
