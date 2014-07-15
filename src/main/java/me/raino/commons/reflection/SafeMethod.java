package me.raino.commons.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang.ArrayUtils;

import me.raino.commons.utility.ReflectionUtility;

/*
 * Inspired by bergerkiller's SafeMethod from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/reflection/SafeMethod.java
 */
@SuppressWarnings("unchecked")
public class SafeMethod<T> implements MethodAccessor<T> {

    private Object from;
    private boolean isStatic;
    private Method method;

    public SafeMethod(Method method) {
        if (!method.isAccessible())
            method.setAccessible(true);
        this.isStatic = Modifier.isStatic(method.getModifiers());
        this.method = method;
    }

    public SafeMethod(Object from, Method method) {
        this(method);
        this.from = from;
    }

    @Override
    public boolean isValid() {
        return this.method != null;
    }

    @Override
    public T invoke(Object... parameters) {
        if (!this.isValid())
            return null;
        boolean needsInstance = this.isStatic ? false : (this.from != null ? false : true);
        Object instance = needsInstance ? parameters[0] : (this.isStatic ? null : this.from);
        if (needsInstance)
            parameters = ArrayUtils.remove(parameters, 0);
        try {
            return (T) this.method.invoke(instance, parameters);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isStatic() {
        return this.isStatic;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public String getName() {
        return this.method.getName();
    }

    @Override
    public Class<T> getReturnType() {
        return (Class<T>) this.method.getReturnType();
    }

    public static <M> SafeMethod<M> of(Class<?> clazz, String method, Class<?>... parameters) {
        Method m = ReflectionUtility.findMethod(clazz, method, parameters);
        return m != null ? new SafeMethod<M>(m) : null;
    }

    public static <M> SafeMethod<M> of(String clazz, String method, Class<?>... parameters) {
        return (SafeMethod<M>) of(ReflectionUtility.getClass(clazz), method, parameters);
    }

    public static <M> SafeMethod<M> of(Object from, String method, Class<?>... parameters) {
        Method m = ReflectionUtility.findMethod(from.getClass(), method, parameters);
        return m != null ? new SafeMethod<M>(from, m) : null;
    }

}
