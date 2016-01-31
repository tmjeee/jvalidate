package com.tmjee.evolution.mata;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author tmjee
 */
public class Accessors {
    private static Accessor IDENTITY = new Accessor() {
        public Object access(Object o) {
            return o;
        }
    };


    public static Accessor identity() {
        return IDENTITY;
    }


    public static Accessor field(final String fieldName) {
        return new Accessor() {
            @Override
            public Object access(Object o) {
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
            }
        };
    }

    public static Accessor method(final String methodName) {
        return new Accessor() {
            @Override
            public Object access(Object o) {
                try {
                    return o == null ? null : o.getClass().getMethod(methodName).invoke(o, null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.toString(), e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e.toString(), e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e.toString(), e);
                }
            }
        };
    }
}
