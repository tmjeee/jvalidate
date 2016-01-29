package com.tmjee.evolution.mata;

import java.util.List;

/**
 * @author tmjee
 */
public class Validation {

    private final List<ValidationBuilder.Entry> e;

    Validation(List<ValidationBuilder.Entry> e) {
        this.e = e;
    }

    public void validate() {
        validate(Resolvers.exceptionThrowingResolver());
    }

    public void validate(Resolver r){
        for (ValidationBuilder.Entry _e : e) {
            for(Validator _v :_e.v) {
                Object o = _e.a.access(_e.o);
                _v.validate(o, _e.n, r);
            }
        }
        r.done();
    }
}
