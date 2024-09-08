package me.renzy.protocol.serializer.types.writeable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class WriteableBoolean extends AbstractWriteable<Boolean> {
    public WriteableBoolean(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public void write(Boolean aBoolean) {
        this.byteBuf.writeBoolean(aBoolean);
    }
}
