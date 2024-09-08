package me.renzy.protocol.serializer.types;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.NonNull;
import me.renzy.protocol.serializer.PacketDataDeserializer;
import me.renzy.protocol.serializer.types.readable.*;
import me.renzy.protocol.serializer.types.writeable.*;

final class PacketDataDeserializerImpl implements PacketDataDeserializer {

    private final ByteBuf byteBuf;

    private final ReadableInt readableInt;
    private final ReadableByte readableByte;
    private final ReadableDouble readableDouble;
    private final ReadableFloat readableFloat;
    private final ReadableLong readableLong;
    private final ReadableBoolean readableBoolean;
    private final ReadableString readableString;

    PacketDataDeserializerImpl(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
        this.readableInt = new ReadableInt(this.byteBuf);
        this.readableDouble = new ReadableDouble(this.byteBuf);
        this.readableLong = new ReadableLong(this.byteBuf);
        this.readableFloat = new ReadableFloat(this.byteBuf);
        this.readableBoolean = new ReadableBoolean(this.byteBuf);
        this.readableString = new ReadableString(this.byteBuf);
        this.readableByte = new ReadableByte(this.byteBuf);
    }



    @Override
    public @NonNull ReadableInt integers() {
        return this.readableInt;
    }

    @Override
    public @NonNull ReadableByte bytes() {
        return this.readableByte;
    }

    @Override
    public @NonNull ReadableLong longs() {
        return this.readableLong;
    }

    @Override
    public @NonNull ReadableFloat floats() {
        return this.readableFloat;
    }

    @Override
    public @NonNull ReadableBoolean booleans() {
        return this.readableBoolean;
    }

    @Override
    public @NonNull ReadableDouble doubles() {
        return this.readableDouble;
    }

    @Override
    public @NonNull ReadableString strings() {
        return this.readableString;
    }
}
