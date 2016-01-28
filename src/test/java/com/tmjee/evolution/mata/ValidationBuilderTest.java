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
    public void test() throws Exception {

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
}
