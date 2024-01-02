package ir.happx.redis.util;

import org.springframework.http.HttpStatus;

public class BanooExeption extends RuntimeException {

    final public static BanooExeption UNKNOWN_ERROR = new BanooExeption("-1", "UNKNOWN_ERROR", HttpStatus.BAD_REQUEST.name());

    private String code;
    private String message;
    private HttpStatus httpStatus;

    public BanooExeption(String statusCode, String message, String httpStatus) {
        this.message = message;
        this.code = statusCode;
        this.httpStatus = HttpStatus.valueOf(httpStatus);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
