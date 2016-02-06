package com.tmjee.evolution.mata;

import static com.tmjee.evolution.mata.Validators.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.List;

/**
 * @author tmjee
 */
public class ValidationBuilderTest {

    static class TestObject {
        public Integer field1;
        public Integer field2;
        private Integer field3;
        private Integer method1;
        private Integer pair1;

        public Integer method1() {
            return method1;
        }

        public Integer pair1() {
            return pair1;
        }
    }


    @Test
    public void test1() throws Exception {

        TestObject testObject = new TestObject();

        try {
            new ValidationBuilder()
                    .using(testObject)
                        .withField("field1", "field1name")
                            .addValidator(notEmpty())
                            .addValidator(lessThan(10))
                        .withMethod("method1", "method1Name")
                            .addValidator(notEmpty())
                            .addValidator(greaterThan(10))
                        .withPair("pairName1", testObject.pair1())
                            .addValidator(notEmpty())
                    .build()
                    .validate();
        } catch(ValidationException e) {
            List<String> messages = e.getMessages();
            for (String message : messages) {
                System.out.println("msg: "+message);
            }

            assertEquals(messages.size(), 3);
            assertThat(messages, hasItem(containsString("field1name is empty")));
            assertThat(messages, hasItem(containsString("method1Name is empty")));
            assertThat(messages, hasItem(containsString("pairName1 is empty")));
        }
    }


    @Test
    public void test2() throws Exception {

        TestObject testObject = new TestObject();
        testObject.field1 = 20;
        testObject.method1 = 5;
        testObject.pair1 = 3;

        try {
            new ValidationBuilder()
                    .using(testObject)
                        .withMethod("method1", "method1Name")
                            .addValidator(notEmpty())
                            .addValidator(greaterThan(10))
                        .withField("field1", "field1name")
                            .addValidator(notEmpty())
                            .addValidator(lessThan(10))
                        .withPair("pairName1", testObject.pair1())
                            .addValidator(notEmpty())
                            .addValidator(lessThan(2))
                    .build()
                    .validate();
        } catch(ValidationException e) {
            List<String> messages = e.getMessages();
            for (String message : messages) {
                System.out.println("msg: "+message);
            }

            assertEquals(messages.size(), 3);
            assertThat(messages, hasItem(containsString("field1name is not less than 10")));
            assertThat(messages, hasItem(containsString("method1Name is not greater than 10")));
            assertThat(messages, hasItem(containsString("pairName1 is not less than 2")));
        }
    }



    @Test
    public void test3() throws Exception {

        TestObject testObject = new TestObject();
        testObject.field1 = 2;
        testObject.method1 = 15;
        testObject.pair1 = 1;

        try {
            new ValidationBuilder()
                    .using(testObject)
                    .withField("field1", "field1name")
                        .addValidator(notEmpty())
                        .addValidator(lessThan(10))
                    .withMethod("method1", "method1Name")
                        .addValidator(notEmpty())
                        .addValidator(greaterThan(10))
                    .withPair("pairName1", testObject.pair1())
                        .addValidator(notEmpty())
                        .addValidator(lessThan(2))
                    .build()
                    .validate();
        } catch(ValidationException e) {
            List<String> messages = e.getMessages();
            for (String message : messages) {
                System.out.println("msg: "+message);
            }

            assertEquals(messages.size(), 0);
        }
    }


    @Test
    public void test4() throws Exception {
        TestObject testObject = new TestObject();

        try {
            new ValidationBuilder()
                    .withPair("field1", testObject.field1)
                    .withPair("field2", testObject.field2)
                        .addValidator(notEmpty())
                .build()
                .validate();
        } catch(ValidationException e) {
            List<String> messages = e.getMessages();
            for(String message: messages) {
                System.out.println("msg: "+message);
            }
            assertEquals(messages.size(), 1);
        }
    }
}
