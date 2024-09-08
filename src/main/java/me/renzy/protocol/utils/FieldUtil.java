package me.renzy.protocol.utils;

import lombok.NonNull;

import java.lang.reflect.Field;

public final class FieldUtil {


    public static Object getFieldStatic(@NonNull String fieldName, Class<?> clazz) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private FieldUtil(){}
}
