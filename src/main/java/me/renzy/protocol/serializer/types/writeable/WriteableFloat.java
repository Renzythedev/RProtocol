package me.renzy.protocol.serializer.types.writeable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class WriteableFloat extends AbstractWriteable<Float> {

    public WriteableFloat(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public void write(Float aFloat) {
        this.byteBuf.writeFloat(aFloat);
    }
}
