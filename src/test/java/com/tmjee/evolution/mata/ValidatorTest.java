package com.tmjee.evolution.mata;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

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

        verify(mockResolver, times(1)).registerError("x", "x is empty");
        verify(mockResolver, times(1)).registerError("y", "y is empty");
        verify(mockResolver, times(1)).registerError("z", "z is empty");
        verifyNoMoreInteractions(mockResolver);
    }

    @Test
    public void test_lessThan() throws Exception {
        Validator v = Validators.lessThan(5);
        v.validate("a", "a", mockResolver);
        v.validate("1", "b", mockResolver);
        v.validate("6", "c", mockResolver);
        v.validate("5", "d", mockResolver);

        verify(mockResolver, times(1)).registerError("a", "a is not a number");
        verify(mockResolver, times(1)).registerError("c", "c is not less than 5");
        verify(mockResolver, times(1)).registerError("d", "d is not less than 5");
        verifyNoMoreInteractions(mockResolver);
    }

    @Test
    public void test_lessThanOrEquals() throws Exception {
        Validator v = Validators.lessThanOrEquals(5);
        v.validate("a", "a", mockResolver);
        v.validate("1", "b", mockResolver);
        v.validate("6", "c", mockResolver);
        v.validate("5", "d", mockResolver);

        verify(mockResolver, times(1)).registerError("a", "a is not a number");
        verify(mockResolver, times(1)).registerError("c", "c is not less than or equals to 5");
        verifyNoMoreInteractions(mockResolver);
    }

    @Test
    public void test_greaterThan() throws Exception {
        Validator v = Validators.greaterThan(5);
        v.validate("a", "a", mockResolver);
        v.validate("2", "b", mockResolver);
        v.validate("", "c", mockResolver);
        v.validate("5", "d", mockResolver);

        verify(mockResolver, times(1)).registerError("a", "a is not a number");
        verify(mockResolver, times(1)).registerError("b", "b is not greater than 5");
        verify(mockResolver, times(1)).registerError("d", "d is not greater than 5");
        verifyNoMoreInteractions(mockResolver);
    }


    @Test
    public void test_greaterThanOrEquals() throws Exception {
        Validator v = Validators.greaterThanOrEquals(5);
        v.validate("a", "a", mockResolver);
        v.validate("2", "b", mockResolver);
        v.validate("", "c", mockResolver);
        v.validate("5", "d", mockResolver);

        verify(mockResolver, times(1)).registerError("a", "a is not a number");
        verify(mockResolver, times(1)).registerError("b", "b is not greater than or equals 5");
        verifyNoMoreInteractions(mockResolver);
    }
}
