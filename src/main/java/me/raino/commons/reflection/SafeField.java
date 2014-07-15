package me.raino.commons.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import me.raino.commons.utility.ReflectionUtility;

/*
 * Inspired by bergerkiller's SafeField from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/reflection/SafeField.java
 */
@SuppressWarnings("unchecked")
public class SafeField<T> implements FieldAccessor<T> {

    private Object from;
    private boolean isStatic;
    private Field field;

    public SafeField(Field field) {
        if (!field.isAccessible())
            field.setAccessible(true);
        this.isStatic = Modifier.isStatic(field.getModifiers());
        this.field = field;
    }

    public SafeField(Object from, Field field) {
        this(field);
        this.from = from;
    }

    @Override
    public boolean isValid() {
        return this.field != null;
    }

    @Override
    public T get(Object instance) {
        if (!this.isValid())
            return null;
        try {
            return (T) this.field.get(this.isStatic() ? null : (this.from != null ? this.from : instance));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public T get() {
        return get(null);
    }

    @Override
    public boolean set(Object object, T value) {
        if (!this.isValid())
            return false;
        try {
            this.field.set(this.isStatic() ? null : (this.from != null ? this.from : object), value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean set(T value) {
        return set(null, value);
    }

    @Override
    public boolean isStatic() {
        return this.isStatic;
    }

    @Override
    public Field getField() {
        return this.field;
    }

    @Override
    public String getName() {
        return this.field.getName();
    }

    @Override
    public Class<?> getType() {
        return this.field.getType();
    }

    public static <F> SafeField<F> of(Class<?> clazz, String field) {
        Field f = ReflectionUtility.findField(clazz, field, null, 0);
        return f != null ? new SafeField<F>(f) : null;
    }

    public static <F> SafeField<F> of(String clazz, String field) {
        return (SafeField<F>) of(ReflectionUtility.getClass(clazz), field);
    }

    public static <F> SafeField<F> of(Object from, String field) {
        Field f = ReflectionUtility.findField(from.getClass(), field, null, 0);
        return f != null ? new SafeField<F>(from, f) : null;
    }
    
    public static <F> SafeField<F> of(Class<?> clazz, Class<F> type, int index) {
        Field f = ReflectionUtility.findField(clazz, null, type, index);
        return f != null ? new SafeField<F>(f) : null;
    }
    
    public static <F> SafeField<F> of(String clazz, Class<F> type, int index) {
        return of(ReflectionUtility.getClass(clazz), type, index);
    }
    
    public static <F> SafeField<F> of(Object from, Class<F> type, int index) {
        Field f = ReflectionUtility.findField(from.getClass(), null, type, index);
        return f != null ? new SafeField<F>(from, f) : null;
    }

}
