package me.raino.commons.reflection;

/*
 * Inspired by bergerkiller's MethodAccessor from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/reflection/MethodAccessor.java
 */
public interface MethodAccessor<T> {

    public boolean isValid();
    
    public T invoke(Object instance, Object... arguments);
    
    public String getName();
    
    public Class<?> getReturnType();
    
}
