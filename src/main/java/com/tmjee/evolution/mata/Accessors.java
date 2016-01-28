package com.tmjee.evolution.mata;

import java.lang.reflect.InvocationTargetException;

/**
 * @author tmjee
 */
public class Accessors {
    private static Accessor IDENTITY = (o,p)->o;


    public static Accessor identity() {
        return IDENTITY;
    }


    public static Accessor field(String fieldName) {
        return (Object o, String s) -> {
            try {
                return o == null ? null :
                    o.getClass().getField(fieldName).get(o);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.toString(), e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e.toString(), e);
            }
        };
    }

    public static Accessor method(String methodName) {
        return (Object o, String s) -> {
            try {
                return o == null ? null : o.getClass().getMethod(methodName).invoke(o, null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.toString(), e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e.toString(), e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.toString(), e);
            }
        };
    }

}
