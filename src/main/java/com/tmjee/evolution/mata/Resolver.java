package com.tmjee.evolution.mata;

/**
 * @author tmjee
 */
public interface Resolver {
    void registerError(String name, String error);
    void done();
}
