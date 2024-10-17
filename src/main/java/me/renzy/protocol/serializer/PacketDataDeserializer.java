package me.renzy.protocol.serializer;

import lombok.NonNull;
import me.renzy.protocol.serializer.types.readable.*;

public interface PacketDataDeserializer {

    @NonNull
    ReadableInt integers();

    @NonNull
    ReadableByte bytes();

    @NonNull
    ReadableLong longs();

    @NonNull
    ReadableFloat floats();

    @NonNull
    ReadableBoolean booleans();

    @NonNull
    ReadableDouble doubles();

    @NonNull
    ReadableString strings();
}
