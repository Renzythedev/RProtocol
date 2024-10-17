package me.renzy.protocol.network.packet.subscription;

import com.google.common.base.Preconditions;
import lombok.NonNull;
import me.renzy.protocol.utils.Delegates;
import me.renzy.protocol.network.packet.Packet;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface PacketSubscriptionBuilder<T extends Packet> {

    @NonNull
    static <T extends Packet> PacketSubscriptionBuilder<T> newBuilder(@NonNull Class<T> packetClass) {
        return new PacketSubscriptionBuilderImpl<>(packetClass);
    }

    @NonNull
    default PacketSubscriptionBuilder<T> expiryIf(@NonNull Predicate<T> predicate) {
        return expiryIf(Delegates.predicateToBiPredicate(predicate),ExpiryTestStage.PRE,ExpiryTestStage.HANDLE);
    }

    @NonNull
    default PacketSubscriptionBuilder<T> expiryAfter(int maxCalls) {
        Preconditions.checkArgument(maxCalls>=1,"maxCalls");
        return expiryIf((subscription, packet) -> maxCalls>=subscription.getCallCounter(),ExpiryTestStage.PRE,ExpiryTestStage.HANDLE);
    }

    @NonNull
    PacketSubscriptionBuilder<T> expiryIf(@NonNull BiPredicate<PacketSubscription<T>,T> predicate, @NonNull ExpiryTestStage... points);

    @NonNull
    default PacketSubscriptionBuilder<T> filter(@NonNull Predicate<T> predicate) {
        return filter(Delegates.predicateToBiPredicate(predicate));
    }

    @NonNull
    PacketSubscriptionBuilder<T> filter(@NonNull BiPredicate<PacketSubscription<T>,T> predicate);

    @NonNull
    PacketSubscriptionBuilder<T> exceptionConsumer(@NonNull BiConsumer<? super T, Throwable> consumer);

    @NonNull
    PacketSubscription<T> handler(@NonNull Consumer<? super T> consumer);
}
