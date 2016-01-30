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
        r.registerError("field1", "an error with field1");
        r.registerError("field2", "an error with field2");
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
