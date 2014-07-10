package me.raino.commons.utility;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import me.raino.commons.reflection.SafeField;
import me.raino.commons.reflection.SafeMethod;

public class ReflectionUtility {

    public static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Method> getMethods(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredMethods());
    }

    public static <T> SafeMethod<T> getMethod(Class<?> clazz, String method) {
        for (Method m : ReflectionUtility.getMethods(clazz))
            if (m.getName().equalsIgnoreCase(method))
                return new SafeMethod<T>(m);
        return null;
    }

    public static List<Field> getFields(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }

    public static <T> SafeField<T> getField(Class<?> clazz, String field) {
        for (Field f : ReflectionUtility.getFields(clazz))
            if (f.getName().equalsIgnoreCase(field))
                return new SafeField<T>(f);
        return null;
    }

    private ReflectionUtility() {}

}
