package cgi.test.restaurant_reservation.Infrastructure;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {
    private final String message;
    private final int errorCode;

    public DataNotFoundException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public DataNotFoundException(ErrorCode error) {
        this(error.getMessage(), error.getErrorCode());
    }
}


