package me.renzy.protocol.serializer.types.writeable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import me.renzy.protocol.interfaces.Writeable;

public abstract class AbstractWriteable<T> implements Writeable<T> {

    final ByteBuf byteBuf;

    protected AbstractWriteable(@NonNull ByteBuf byteBuf) {
        this.byteBuf= byteBuf;
    }
}
