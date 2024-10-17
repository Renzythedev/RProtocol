package me.renzy.protocol.serializer.types.writeable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class WriteableLong extends AbstractWriteable<Long>{
    public WriteableLong(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public void write(Long aLong) {
        this.byteBuf.writeLong(aLong);
    }
}
