package ir.happx.redis.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class BanooExeptionResponsEntity {

    private String code;
    private String message;
    private HttpStatus httpStatus;
    private Date date;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Date getDate() {
        return date;
    }

    public BanooExeptionResponsEntity(BanooExeption exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
        this.httpStatus = exception.getHttpStatus();
        this.date = new Date();
    }
}
