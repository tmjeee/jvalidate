package com.tmjee.evolution.mata;

import static java.lang.String.format;

/**
 * @author tmjee
 */
public class Validators {

    private static final Validator NOT_EMPTY = (Object o, String n, Resolver r)->{
        if (o == null || o.toString().trim().isEmpty()) {
           r.registerError(format("%s is empty", n));
        }
    };


    public static final Validator notEmpty() {
        return NOT_EMPTY;
    }


    public static final Validator lessThan(int m) {
        return (Object o, String n, Resolver r) ->{
           if (o != null && (o instanceof Number)) {
               Number _n = (Number)o;
               if (!(_n.intValue()<m)) {
                   r.registerError(format("%s is not less than %s", n, m));
               }
           }
        };
    }

    public static final Validator lessThanOrEquals(int m) {
        return (Object o, String n, Resolver r) ->{
            if (o != null && (o instanceof Number)) {
                Number _n = (Number)o;
                if (!(_n.intValue()<=m)) {
                    r.registerError(format("%s is not less than or equals to %s", n, m));
                }
            }
        };
    }


    public static final Validator greaterThan(int m) {
        return (Object o, String n, Resolver r) ->{
            if (o != null && (o instanceof Number)) {
                Number _n = (Number)o;
                if (!(_n.intValue()>m)) {
                    r.registerError(format("%s is not greater than %s", n, m));
                }
            }
        };
    }

    public static final Validator greaterThanOrEquals(int m) {
        return (Object o, String n, Resolver r) ->{
            if (o != null && (o instanceof Number)) {
                Number _n = (Number)o;
                if (!(_n.intValue()>=m)) {
                    r.registerError(format("%s is not greater than or equals %s", n, m));
                }
            }
        };
    }



}
