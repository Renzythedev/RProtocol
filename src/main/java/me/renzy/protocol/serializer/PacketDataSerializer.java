package me.renzy.protocol.serializer;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import me.renzy.protocol.serializer.types.writeable.*;

public interface PacketDataSerializer {

    @NonNull
    WriteableByte bytes();

    @NonNull
    WriteableInt integers();

    @NonNull
    WriteableFloat floats();

    @NonNull
    WriteableLong longs();

    @NonNull
    WriteableDouble doubles();

    @NonNull
    WriteableBoolean booleans();

    @NonNull
    WriteableString strings();

    @NonNull
    ByteBuf toByteBuffer();
}
