package com.tmjee.evolution.mata;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * @author tmjee
 */
public class ResolverTest {

    @Test
    public void test1() throws Exception {
        Resolver r = Resolvers.exceptionThrowingResolver();
        r.registerError("test1");
        r.registerError("test2");
        try {
            r.done();
        } catch(ValidationException e) {
            assertNotNull(e.getMessages());
            assertEquals(e.getMessages().size(), 2);
        }
    }

    @Test
    public void test2() throws Exception {
        Resolver r = Resolvers.exceptionThrowingResolver();
        r.done();
    }
}
