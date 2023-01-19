package com.klid.demospringbootretryable;

/**
 * @author Ivan Kaptue
 */
public class RetryableException extends RuntimeException {

    public RetryableException(String message) {
        super(message);
    }
}
