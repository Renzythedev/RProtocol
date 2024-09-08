package me.renzy.protocol.serializer.types.writeable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class WriteableInt extends AbstractWriteable<Integer> {

    public WriteableInt(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public void write(Integer integer) {
        this.byteBuf.writeInt(integer);
    }
}
