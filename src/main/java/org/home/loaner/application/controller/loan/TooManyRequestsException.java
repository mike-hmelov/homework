package org.home.loaner.application.controller.loan;

class TooManyRequestsException extends RuntimeException {
    TooManyRequestsException(String country) {
        super("Too many requests from location: " + country);
    }
}
