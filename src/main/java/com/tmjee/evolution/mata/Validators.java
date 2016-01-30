package com.tmjee.evolution.mata;

import java.text.NumberFormat;
import java.text.ParseException;

import static java.lang.String.format;

/**
 * @author tmjee
 */
public class Validators {

    private static final Validator NOT_EMPTY = (Object o, String n, Resolver r)->{
        if (o == null || o.toString().trim().isEmpty()) {
           r.registerError(n, format("%s is empty", n));
        }
    };

    public static abstract class AbstractNumberValidator implements Validator {
        @Override
        public void validate(Object o, String name, Resolver r) {
            if (o != null && (!o.toString().trim().isEmpty())) {
                Number _n = null;
                if (o instanceof Number) {
                    _n = (Number) o;
                } else {
                    try {
                        _n = NumberFormat.getInstance().parse(o.toString());
                    } catch (ParseException e) {
                        r.registerError(name, format("%s is not a number", name));
                        return;
                    }
                }
                doValidate(_n, name, r);
            }
        }

        protected abstract void doValidate(Number n, String name, Resolver r);
    }


    public static final Validator notEmpty() {
        return NOT_EMPTY;
    }


    public static final Validator lessThan(int m) {
        return new AbstractNumberValidator() {
            @Override
            protected void doValidate(Number _n, String n, Resolver r) {
                if (!(_n.intValue()<m)) {
                    r.registerError(n, format("%s is not less than %s", n, m));
                }
            }
        };
    }

    public static final Validator lessThanOrEquals(int m) {
        return new AbstractNumberValidator() {
            @Override
            protected void doValidate(Number _n, String n, Resolver r) {
                if (!(_n.intValue()<=m)) {
                    r.registerError(n, format("%s is not less than or equals to %s", n, m));
                }
            }
        };
    }


    public static final Validator greaterThan(int m) {
        return new AbstractNumberValidator() {
            @Override
            protected void doValidate(Number _n, String n, Resolver r) {
                if (!(_n.intValue()>m)) {
                    r.registerError(n, format("%s is not greater than %s", n, m));
                }
            }
        };
    }

    public static final Validator greaterThanOrEquals(int m) {
        return new AbstractNumberValidator() {
            @Override
            protected void doValidate(Number _n, String n, Resolver r) {
                if (!(_n.intValue()>=m)) {
                    r.registerError(n, format("%s is not greater than or equals %s", n, m));
                }
            }
        };
    }
}
