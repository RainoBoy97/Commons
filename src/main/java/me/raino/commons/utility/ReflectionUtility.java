package me.raino.commons.utility;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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

    public static Method getMethod(Class<?> clazz, String method) {
        for (Method m : ReflectionUtility.getMethods(clazz))
            if (m.getName().equalsIgnoreCase(method))
                return m;
        return null;
    }

    public static List<Field> getFields(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }

    public static Field getField(Class<?> clazz, String field) {
        for (Field f : ReflectionUtility.getFields(clazz))
            if (f.getName().equalsIgnoreCase(field))
                return f;
        return null;
    }

    private ReflectionUtility() {}

}
