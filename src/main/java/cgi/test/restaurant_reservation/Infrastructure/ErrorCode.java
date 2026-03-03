package cgi.test.restaurant_reservation.Infrastructure;

import lombok.Getter;

@Getter
public enum ErrorCode {

    TABLE_NOT_FOUND("Lauda ei leitud", 111);

    private final String message;
    private final int errorCode;

    ErrorCode(String message, int errorCode){
        this.message = message;
        this.errorCode = errorCode;
    }

}
