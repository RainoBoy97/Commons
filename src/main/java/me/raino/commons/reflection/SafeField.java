package me.raino.commons.reflection;

import java.lang.reflect.Field;

/*
 * Inspired by bergerkiller's SafeField from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/reflection/SafeField.java
 */
public class SafeField<T> implements FieldAccessor<T> {

    private Object from;
    private Field field;

    public SafeField(Object from, Field field) {
        this.from = from;
        if (!field.isAccessible())
            field.setAccessible(true);
        this.field = field;
    }

    @Override
    public boolean isValid() {
        return this.field != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get() {
        if (!this.isValid())
            return null;
        try {
            return (T) this.field.get(this.from);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean set(Object object, T value) {
        if (!this.isValid())
            return false;
        try {
            this.field.set(object, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getName() {
        return this.field.getName();
    }

    @Override
    public Class<?> getType() {
        return this.field.getType();
    }

    public static <T> SafeField<T> of(Object from, String field) {
        Field f = SafeField.findField(from.getClass(), field);
        return f != null ? new SafeField<T>(from, f) : null;
    }

    private static Field findField(Class<?> clazz, String field) {
        Class<?> temp = clazz;
        while (temp != null) {
            try {
                return temp.getDeclaredField(field);
            } catch (Exception e) {
                temp = temp.getSuperclass();
            }
        }
        return null;
    }

}
