package me.raino.commons.utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import me.raino.commons.reflection.SafeConstructor;
import me.raino.commons.reflection.SafeField;
import me.raino.commons.reflection.SafeMethod;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;

import com.google.common.collect.Lists;

public class ReflectionUtility {

    private static String OBC = Bukkit.getServer().getClass().getPackage().getName();
    private static String NMS = StringUtils.replace(OBC, "org.bukkit.craftbukkit", "net.minecraft.server");

    public static Class<?> getClass(String clazz) {
        try {
            return Class.forName(getFullPackage(clazz));
        } catch (Exception e) {
            return null;
        }
    }

    public static Class<?> getOBCClass(String clazz) {
        return getClass(OBC + "." + clazz);
    }

    public static Class<?> getNMSClass(String clazz) {
        return getClass(NMS + "." + clazz);
    }

    public static List<SafeField<?>> getFields(Object object) {
        List<SafeField<?>> fields = Lists.newArrayList();
        for (Field f : object.getClass().getDeclaredFields())
            fields.add(new SafeField<Object>(object, f));
        return fields;
    }

    public static List<SafeMethod<?>> getMethods(Object object) {
        List<SafeMethod<?>> methods = Lists.newArrayList();
        for (Method m : object.getClass().getDeclaredMethods())
            methods.add(new SafeMethod<Object>(object, m));
        return methods;
    }

    public static List<SafeConstructor<?>> getConstructors(Object object) {
        List<SafeConstructor<?>> constructors = Lists.newArrayList();
        for (Constructor<?> c : object.getClass().getDeclaredConstructors())
            constructors.add(new SafeConstructor<Object>(c));
        return constructors;
    }

    public static Field findField(Class<?> clazz, String field, Class<?> type, int index) {
        int temp = index;
        for (Field f : clazz.getDeclaredFields())
            if ((field == null || f.getName().equals(field)) && type.isAssignableFrom(f.getType()) && temp-- <= 0)
                return f;
        if (clazz.getSuperclass() != null)
            return findField(clazz.getSuperclass(), field, type, index);
        return null;
    }

    public static Method findMethod(Class<?> clazz, String method, Class<?>... parameters) {
        for (Method m : clazz.getDeclaredMethods())
            if (m.getName().equals(method) && Arrays.equals(m.getParameterTypes(), parameters))
                return m;
        if (clazz.getSuperclass() != null)
            return findMethod(clazz.getSuperclass(), method, parameters);
        return null;
    }

    public static Constructor<?> findConstructor(Class<?> clazz, Class<?>... parameters) {
        for (Constructor<?> c : clazz.getConstructors())
            if (Arrays.equals(c.getParameterTypes(), parameters))
                return c;
        if (clazz.getSuperclass() != null)
            return findConstructor(clazz.getSuperclass(), parameters);
        return null;
    }

    private static String getFullPackage(String path) {
        path = StringUtils.replace(path, "{nms}", NMS);
        path = StringUtils.replace(path, "{obc}", OBC);
        return path;
    }

    private ReflectionUtility() {}

}
