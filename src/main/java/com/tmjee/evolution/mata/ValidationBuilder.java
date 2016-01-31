package com.tmjee.evolution.mata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * @author tmjee
 */
public class ValidationBuilder {

    private static final Logger log = LoggerFactory.getLogger(ValidationBuilder.class);

    static class Context {
        Object o;
        Accessor a;
        String n;
        List<Validator> v = new ArrayList<>();

        List<Entry> e = new ArrayList<>();
        void validateAndAdd() {
            if (a != null &&
                n != null &&
                !v.isEmpty()) {
                e.add(new Entry(o, a, n, v));
                log.debug("---> add"+n);
                o = null;
                a = null;
                n = null;
                v = new ArrayList<>();
            } else {
                log.info(format("validation not added o=%s,a=%s,n=%s,v=%s", o, a, n, v));
            }
        }
    }

    static class Entry {
        final Object o;
        final Accessor a;
        final String n;
        final List<Validator> v;
        Entry(Object o, Accessor a, String n, List<Validator> v) {
            this.o = o;
            this.a = a;
            this.n = n;
            this.v = v;
        }
    }

    private Context context;

    public ValidationBuilder() {
        context = new Context();
    }


    public Step_DefineProperty using(Object o) {
        return new Step_DefineRoot().using(o);
    }

    public Step_DefineValidator withPair(String name, Object o) {
        return new Step_DefineRoot().withPair(name, o);
    }


    public class Step_DefineRoot {
        public Step_DefineProperty using(Object o) {
            context.o = o;
            return new Step_DefineProperty();
        }

        public Step_DefineValidator withPair(String name, Object o) {
            context.o = o;
            context.n = name;
            context.a = Accessors.identity();
            return new Step_DefineValidator();
        }
    }


    public class Step_DefineProperty {
        public Step_DefineValidator withField(String fieldName, String name) {
            context.n = name;
            context.a = Accessors.field(fieldName);
            return new Step_DefineValidator();
        }

        public Step_DefineValidator withMethod(String methodName, String name) {
            context.n = name;
            context.a = Accessors.method(methodName);
            return new Step_DefineValidator();
        }
    }


    public class Step_DefineValidator {
        public Step_DefineValidator addValidator(Validator v) {
            context.v.add(v);
            return this;
        }

        public Step_DefineValidator withField(String fieldName, String name) {
            Object oldO = context.o;
            context.validateAndAdd();
            context.o = oldO;
            context.n = name;
            context.a = Accessors.field(fieldName);
            return this;
        }

        public Step_DefineValidator withMethod(String methodName, String name) {
            Object oldO = context.o;
            context.validateAndAdd();
            context.o = oldO;
            context.n = name;
            context.a = Accessors.method(methodName);
            return this;
        }

        public Step_DefineValidator withPair(String name, Object o) {
            context.validateAndAdd();
            context.o = o;
            context.n = name;
            context.a = Accessors.identity();
            return this;
        }

        public Step_DefineProperty using(Object o) {
            context.validateAndAdd();
            return new Step_DefineRoot().using(o);
        }

        public Validation build() {
            context.validateAndAdd();
            return new Validation(new ArrayList<Entry>(context.e));
        }
    }
}
