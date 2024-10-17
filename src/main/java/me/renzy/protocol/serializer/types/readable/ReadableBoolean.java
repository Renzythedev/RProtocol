package me.renzy.protocol.serializer.types.readable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import me.renzy.protocol.interfaces.Readable;

public final class ReadableBoolean extends AbstractReadable<Boolean> {

    public ReadableBoolean(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public Boolean read() {
        return this.byteBuf.readBoolean();
    }
}
