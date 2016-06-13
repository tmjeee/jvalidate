package com.tmjee.evolution.mata;

/**
 * @author tmjee
 */
@FunctionalInterface
public interface Validator {
    void validate(Object o, String name, Resolver r);
}
