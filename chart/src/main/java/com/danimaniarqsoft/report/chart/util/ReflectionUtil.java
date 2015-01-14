package com.danimaniarqsoft.report.chart.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danimaniarqsoft.report.chart.exceptions.DslException;
import com.danimaniarqsoft.report.chart.exceptions.FieldNotExistException;
import com.danimaniarqsoft.report.chart.exceptions.MethodNotPresentException;

/**
 * Java Parsing class used for parsing Java classes and make format jobs
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class ReflectionUtil {
    private static final Logger LOG = LoggerFactory
            .getLogger(ReflectionUtil.class);

    private ReflectionUtil() {
    }

    /**
     * Convert a String name of a property of some class to getter format.
     * <p>
     * Ej. if we pass "someProperty" the method returns "getSomeProperty"
     * <p>
     * It Kind of method is very useful for parsing operations.
     * 
     * @param property
     * @return
     */
    public static String toGetterFormat(String field) {
        return "get" + toCamelCaseFormat(field);
    }

    public static String toSetterFormat(String field) {
        return "set" + toCamelCaseFormat(field);
    }

    public static String toCamelCaseFormat(String field) {
        return Character.toUpperCase(field.charAt(0))
                + field.substring(1, field.length());
    }

    /**
     * This method return the getter method of some kind of class given some
     * property. This method is very useful for parsing and reflection
     * operations.
     * 
     * @param propertyName
     *            the property of the class whom we want search the getter
     *            method into a class.
     * @param clazz
     *            the class where the getter method will be searched
     * @return the Getter Method of the propertyName param
     */
    public static <T> Method searchGetterMethod(String fieldName, Class<T> clazz) {
        try {
            return clazz.getMethod(toGetterFormat(fieldName), new Class[] {});
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new MethodNotPresentException("The getter method for "
                    + fieldName + " does not exist");

        }
    }

    public static <T> Method searchSetterMethod(String fieldName, Class<T> clazz) {
        try {
            Field field = searchField(fieldName, clazz);
            return clazz.getMethod(toSetterFormat(fieldName),
                    new Class[] { field.getType() });
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new MethodNotPresentException("The getter method for "
                    + fieldName + " does not exist");

        }
    }

    public static <T> Field searchField(String fieldName, Class<T> clazz) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (SecurityException | NoSuchFieldException e) {
            throw new FieldNotExistException("The field " + fieldName
                    + " does not exist on the class \"" + clazz + "\"");

        }
    }

    public static <T> Class<?> getClassPropertyType(String type, Class<T> clazz) {
        try {
            Field field = clazz.getDeclaredField(type);
            Class<?> typeClass = field.getType();
            return typeClass;
        } catch (NoSuchFieldException e) {
            throw new DslException("The typeOf parameter does not exist", e);
        } catch (SecurityException e) {
            throw new DslException("SecurityException", e);
        }
    }

    public static <T> boolean isNumberClass(Class<T> testClass) {
        if (testClass == Byte.class || testClass == Double.class
                || testClass == Float.class || testClass == Integer.class
                || testClass == Short.class || testClass == Long.class
                || testClass.toString().equals("int")
                || testClass.toString().equals("double")
                || testClass.toString().equals("float")) {
            return true;

        } else {
            return false;
        }
    }

    public static <P> Object invokeGetterMethod(Method method, P instance) {
        try {
            return method.invoke(instance, new Object[] {});
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new DslException("The method can not be invoked", e);
        }
    }

    public static Object getFieldValue(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        Method getterMethod = searchGetterMethod(fieldName, clazz);
        return invokeGetterMethod(getterMethod, object);
    }

    public static void setFieldValue(Object targetObject, String fieldName,
            Object value) {
        Method setterMethod = searchSetterMethod(fieldName,
                targetObject.getClass());
        invokeSetterMethod(setterMethod, targetObject, value);
    }

    public static <P> Object invokeSetterMethod(Method method, P instance,
            Object value) {
        try {
            return method.invoke(instance, new Object[] { value });
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new DslException("The method can not be invoked", e);
        }
    }

    /**
     * Method used to create a new Instance from a specific Class.
     * <p>
     * If is not possible to create a new Object, the method returns null.
     * 
     * @param clazz
     * @return a instance of class
     */
    public static <T> T createNewInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            LOG.error("No fue posible crear una instancia de la clase: ", e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            LOG.error("Acceso Ilegal a la clase: ", e);
            e.printStackTrace();
        }
        return null;
    }
}
