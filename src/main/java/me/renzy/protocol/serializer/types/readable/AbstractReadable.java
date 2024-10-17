package me.renzy.protocol.serializer.types.readable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import me.renzy.protocol.interfaces.Readable;

public abstract class AbstractReadable<T> implements Readable<T> {

    protected final ByteBuf byteBuf;

    protected AbstractReadable(@NonNull ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }
}
