package cgi.test.restaurant_reservation.Infrastructure;

import lombok.Getter;

@Getter
public enum ErrorCode {

    RESTAURANT_TABLE_NOT_FOUND("Lauda ei leitud", 111),
    RESTAURANT_TABLE_ALREADY_BOOKED("Laud on juba sellel kellaajal broneeritud", 112),
    RESTAURANT_TABLE_TOO_SMALL("Laud on liiga väike", 113),
    RESERVATION_NOT_FOUND("Broneerigut ei leitud", 114),
    CUSTOMER_NAME_REQUIRED("Palun sisesta nimi", 115),
    CUSTOMER_PHONE_NUMBER_REQUIRED("Palun sisesta telefoninumber", 116);

    private final String message;
    private final int errorCode;

    ErrorCode(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

}
