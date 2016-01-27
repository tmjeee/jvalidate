package com.tmjee.evolution.mata;

/**
 * @author tmjee
 */
public class ValidationBuilder {

    static class Context {
    }

    static class Entry {

    }

    private Context context;

    public ValidationBuilder() {
        context = new Context();
    }


    public Step_DefineProperty using(Object o) {
        return new Step_DefineRoot().using(o);
    }


    public class Step_DefineRoot {
        public Step_DefineProperty using(Object o) {
            return new Step_DefineProperty();
        }

        public Step_DefineValidator withPair(String name, Object o) {
            return new Step_DefineValidator();
        }
    }


    public class Step_DefineProperty {
        public Step_DefineValidator withProperty(String prop) {
            return new Step_DefineValidator();
        }

    }


    public class Step_DefineValidator {
        public Step_DefineValidator addValidator(Validator v) {
            return this;
        }

        public Step_DefineValidator withProperty(String prop) {
            return new Step_DefineValidator();
        }

        public Step_DefineProperty using(Object o) {
            return new Step_DefineRoot().using(o);
        }

        public Validation build() {
            return new Validation();
        }
    }
}
