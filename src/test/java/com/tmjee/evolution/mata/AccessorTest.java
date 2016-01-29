package com.tmjee.evolution.mata;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author tmjee
 */
public class AccessorTest {

    static class TestObject {
        public Integer field1;
        private Integer method1;
        public Integer method1() {
            return method1;
        }
    }

    private TestObject t;

    @Before
    public void before() {
        t = new TestObject();
    }


    @Test
    public void test_field() {
        Accessor a = Accessors.field("field1");

        assertNull(a.access(t));

        t.field1 = 2;
        assertEquals(a.access(t), 2);
    }

    @Test
    public void test_method() {
        Accessor a = Accessors.method("method1");

        assertNull(a.access(t));

        t.method1=2;
        assertEquals(a.access(t), 2);
    }

    @Test
    public void test_identity() {
        TestObject t = new TestObject();
        assertEquals(Accessors.identity().access(t), t);
    }
}
