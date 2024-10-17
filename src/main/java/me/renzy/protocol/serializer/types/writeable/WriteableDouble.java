package me.renzy.protocol.serializer.types.writeable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public final class WriteableDouble extends AbstractWriteable<Double> {
    public WriteableDouble(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public void write(Double aDouble) {
        this.byteBuf.writeDouble(aDouble);
    }
}
