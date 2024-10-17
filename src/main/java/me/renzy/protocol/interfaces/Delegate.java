package me.renzy.protocol.interfaces;

import lombok.NonNull;

public interface Delegate<T> {

    @NonNull
    T getDelegate();
}
