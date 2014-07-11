package me.raino.commons.reflection;

import java.lang.reflect.Method;

/*
 * Inspired by bergerkiller's SafeMethod from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/reflection/SafeMethod.java
 */
public class SafeMethod<T> implements MethodAccessor<T> {

    private Object from;
    private Method method;

    public SafeMethod(Object from, Method method) {
        this.from = from;
        if (!method.isAccessible())
            method.setAccessible(true);
        this.method = method;
    }

    @Override
    public boolean isValid() {
        return this.method != null;
    }
    
    @SuppressWarnings("unchecked")
    public T invoke(Object... arguments) {
        if (!this.isValid())
            return null;
        try {
            return (T) this.method.invoke(this.from, arguments);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getName() {
        return this.method.getName();
    }

    @Override
    public Class<?> getReturnType() {
        return this.method.getReturnType();
    }
    
    public static <M> SafeMethod<M> of(Object from, String method, Class<?>... arguments) {
        Method m = SafeMethod.findMethod(from.getClass(), method, arguments);
        return m != null ? new SafeMethod<M>(from, m) : null;
    }
    
    private static Method findMethod(Class<?> clazz, String method, Class<?>... arguments) {
        Class<?> temp = clazz;
        while (temp != null) {
            try {
                return temp.getDeclaredMethod(method, arguments);
            } catch (Exception e) {
                temp = temp.getSuperclass();
            }
        }
        for (Class<?> i : clazz.getInterfaces()) {
            try {
                return i.getDeclaredMethod(method, arguments);
            } catch (Exception e) {}
        }
        return null;
    }

}
