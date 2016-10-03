package org.home.mike.application.service.client;

public class InvalidClientForLoanException extends RuntimeException {
    InvalidClientForLoanException(String message) {
        super(message);
    }
}
