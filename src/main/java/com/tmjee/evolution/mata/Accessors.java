package com.tmjee.evolution.mata;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author tmjee
 */
public class Accessors {
    private static Accessor IDENTITY = (o)->o;


    public static Accessor identity() {
        return IDENTITY;
    }


    public static Accessor field(String fieldName) {
        return (Object o) -> {
            Field f = null;
            try {
                f = o == null ? null :
                    o.getClass().getField(fieldName);
                f.setAccessible(true);
                return f.get(o);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.toString(), e);
            } catch (NoSuchFieldException e) {
                try {
                    f = o.getClass().getDeclaredField(fieldName);
                    f.setAccessible(true);
                    return f.get(o);
                } catch (NoSuchFieldException e1) {
                    throw new RuntimeException(e.toString(), e);
                } catch (IllegalAccessException e1) {
                    throw new RuntimeException(e.toString(), e);
                }
            }
        };
    }

    public static Accessor method(String methodName) {
        return (Object o) -> {
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
