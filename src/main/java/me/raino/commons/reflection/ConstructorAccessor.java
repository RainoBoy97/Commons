package me.raino.commons.reflection;

import java.lang.reflect.Constructor;

public interface ConstructorAccessor<T> {

    public boolean isValid();

    public T newInstance(Object... parameters);

    public Constructor<?> getConstructor();

    public String getName();

}
