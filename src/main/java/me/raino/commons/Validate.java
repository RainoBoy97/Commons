package me.raino.commons;

import java.util.Collection;

public final class Validate {

    public static <T> T notNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }

    public static <T> T notNull(T obj, String message, Object... arguments) {
        if (obj == null)
            throw new NullPointerException(Txt.parse(message, arguments));
        return obj;
    }
    
    public static <T> T fixNull(T obj, T def) {
        return obj != null ? obj : def;
    }

    public static void isTrue(boolean expression) {
        if (!expression)
            throw new IllegalArgumentException();
    }

    public static void isTrue(boolean expression, String message, Object... arguments) {
        if (!expression)
            throw new IllegalArgumentException(Txt.parse(message, arguments));
    }

    public static void isFalse(boolean expression) {
        if (expression)
            throw new IllegalArgumentException();
    }

    public static void isFalse(boolean expression, String message, Object... arguments) {
        if (expression)
            throw new IllegalArgumentException(Txt.parse(message, arguments));
    }

    public static Collection<?> notEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty())
            throw new IllegalArgumentException();
        return collection;
    }

    public static Collection<?> notEmpty(Collection<?> collection, String message, Object... arguments) {
        if (collection.isEmpty())
            throw new IllegalArgumentException(Txt.parse(message, arguments));
        return collection;
    }

    private Validate() {}

}
