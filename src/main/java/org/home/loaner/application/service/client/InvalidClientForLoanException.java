package org.home.loaner.application.service.client;

public class InvalidClientForLoanException extends RuntimeException {
    public InvalidClientForLoanException(String message) {
        super(message);
    }
}
