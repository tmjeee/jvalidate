package com.tmjee.evolution.mata;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author tmjee
 */
public class ValidatorTest {

    private Resolver mockResolver;

    @Before
    public void before() {
        mockResolver = Mockito.mock(Resolver.class);
    }

    @Test
    public void test_notEmpty() throws Exception {

        Validator v = Validators.notEmpty();
        v.validate("", "x", mockResolver);
        v.validate(null, "y", mockResolver);
        v.validate(" ", "z", mockResolver);
        v.validate(" a ", "a", mockResolver);

        verify(mockResolver, times(1)).registerError("x is empty");
        verify(mockResolver, times(1)).registerError("y is empty");
        verify(mockResolver, times(1)).registerError("z is empty");
    }

    public void test_() throws Exception {
        Validator v = Validators.greaterThan(5);
        v.validate("", "a", mockResolver);
        v.validate("2", "b", mockResolver);

        verify(mockResolver, times(1)).registerError("a is not greater than 5");
    }
}
