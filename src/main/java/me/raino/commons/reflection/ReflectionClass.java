package me.raino.commons.reflection;

/*
 * Inspired by bergerkiller's ClassTemplate from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/reflection/ClassTemplate.java
 */
public class ReflectionClass<T> {

    private Class<T> clazz;

    private ReflectionClass(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Class<T> getType() {
        return this.clazz;
    }

    public <F> SafeField<F> getField(String field) {
        return SafeField.of(this.clazz, field);
    }

    public <M> SafeMethod<M> getMethod(String method, Class<?>... arguments) {
        return SafeMethod.of(clazz, method, arguments);
    }

    public static <T> ReflectionClass<T> of(Class<T> clazz) {
        return new ReflectionClass<T>(clazz);
    }

}
