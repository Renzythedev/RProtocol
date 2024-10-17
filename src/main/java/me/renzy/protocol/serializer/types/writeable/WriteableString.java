package me.renzy.protocol.serializer.types.writeable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

import java.nio.charset.StandardCharsets;

public final class WriteableString extends AbstractWriteable<String> {
    public WriteableString(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public void write(String s) {
        this.byteBuf.writeInt(s.length());
        this.byteBuf.writeBytes(s.getBytes(StandardCharsets.UTF_8));
    }
}
