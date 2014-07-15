package me.raino.commons.reflection;

import java.lang.reflect.Method;

/*
 * Inspired by bergerkiller's MethodAccessor from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/reflection/MethodAccessor.java
 */
public interface MethodAccessor<T> {

    public boolean isValid();

    public T invoke(Object... parameters);

    public boolean isStatic();

    public Method getMethod();

    public String getName();

    public Class<T> getReturnType();

}
