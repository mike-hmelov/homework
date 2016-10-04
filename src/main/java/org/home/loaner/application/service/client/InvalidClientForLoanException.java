package org.home.loaner.application.service.client;

public class InvalidClientForLoanException extends RuntimeException {
    InvalidClientForLoanException(String message) {
        super(message);
    }
}
