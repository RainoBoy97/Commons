package me.raino.commons.reflection;

import java.lang.reflect.Constructor;

import me.raino.commons.utility.ReflectionUtility;

/*
 * Inspired by bergerkiller's SafeConstructor from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/reflection/SafeConstructor.java
 */
@SuppressWarnings("unchecked")
public class SafeConstructor<T> implements ConstructorAccessor<T> {

    private Constructor<?> constructor;

    public SafeConstructor(Constructor<?> c) {
        if (!c.isAccessible())
            c.setAccessible(true);
        this.constructor = c;
    }

    @Override
    public boolean isValid() {
        return this.constructor != null;
    }

    @Override
    public T newInstance(Object... arguments) {
        if (!isValid())
            return null;
        try {
            return (T) this.constructor.newInstance(arguments);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Constructor<?> getConstructor() {
        return this.constructor;
    }

    @Override
    public String getName() {
        return this.constructor.getName();
    }

    public static <C> SafeConstructor<C> of(Class<?> clazz, Class<?>... parameters) {
        Constructor<?> c = ReflectionUtility.findConstructor(clazz, parameters);
        return c != null ? new SafeConstructor<C>(c) : null;
    }

    public static <C> SafeConstructor<C> of(String clazz, Class<?>... parameters) {
        return (SafeConstructor<C>) of(ReflectionUtility.getClass(clazz), parameters);
    }

    public static <C> SafeConstructor<C> of(Object object, Class<?>... parameters) {
        return (SafeConstructor<C>) of(object.getClass(), parameters);
    }

}
