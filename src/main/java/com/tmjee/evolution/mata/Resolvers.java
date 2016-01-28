package com.tmjee.evolution.mata;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tmjee
 */
public class Resolvers {

    public static Resolver exceptionThrowingResolver() {
        return new ExceptionThrowingResolver();
    }



    public static class ExceptionThrowingResolver implements Resolver {
        private final List<String> messages = new ArrayList<>();

        @Override
        public void registerError(String error) {
           messages.add(error);
        }

        @Override
        public void done() {
            if (!messages.isEmpty()) {
               throw new ValidationException(messages);
            }
        }
    }
}
