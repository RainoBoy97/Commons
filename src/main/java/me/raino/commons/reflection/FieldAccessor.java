package me.raino.commons.reflection;

/*
 * Inspired by bergerkiller's FieldAccessor from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/reflection/FieldAccessor.java
 */
public interface FieldAccessor<T> {

    public boolean isValid();
    
    public T get();
    
    public boolean set(Object object, T value);
    
    public String getName();
    
    public Class<?> getType();
    
}
