package me.renzy.protocol.serializer.types.readable;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

import java.nio.charset.StandardCharsets;

public final class ReadableString extends AbstractReadable<String>{
    public ReadableString(@NonNull ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public String read() {
        byte[] data = new byte[this.byteBuf.readInt()];
        this.byteBuf.readBytes(data);
        return new String(data, StandardCharsets.UTF_8);
    }
}
