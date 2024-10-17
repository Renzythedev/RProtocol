package me.renzy.protocol.serializer.types;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.NonNull;
import me.renzy.protocol.serializer.PacketDataSerializer;
import me.renzy.protocol.serializer.types.writeable.*;

final class PacketDataSerializerImpl implements PacketDataSerializer {

    private final ByteBuf byteBuf;

    private final WriteableInt writeableInt;
    private final WriteableByte writeableByte;
    private final WriteableDouble writeableDouble;
    private final WriteableFloat writeableFloat;
    private final WriteableLong writeableLong;
    private final WriteableBoolean writeableBoolean;
    private final WriteableString writeableString;

    PacketDataSerializerImpl(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
        this.writeableInt = new WriteableInt(this.byteBuf);
        this.writeableDouble = new WriteableDouble(this.byteBuf);
        this.writeableLong = new WriteableLong(this.byteBuf);
        this.writeableFloat = new WriteableFloat(this.byteBuf);
        this.writeableBoolean = new WriteableBoolean(this.byteBuf);
        this.writeableString = new WriteableString(this.byteBuf);
        this.writeableByte = new WriteableByte(this.byteBuf);
    }

    @Override
    public @NonNull WriteableByte bytes() {
        return this.writeableByte;
    }

    @Override
    public @NonNull WriteableInt integers() {
        return this.writeableInt;
    }

    @Override
    public @NonNull WriteableFloat floats() {
        return this.writeableFloat;
    }

    @Override
    public @NonNull WriteableLong longs() {
        return this.writeableLong;
    }

    @Override
    public @NonNull WriteableDouble doubles() {
        return this.writeableDouble;
    }

    @Override
    public @NonNull WriteableBoolean booleans() {
        return this.writeableBoolean;
    }

    @Override
    public @NonNull WriteableString strings() {
        return this.writeableString;
    }

    @Override
    public @NonNull ByteBuf toByteBuffer() {
        return this.byteBuf;
    }
}
