package org.demir.dormitory.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse<T> {
    private String message;
    private T data;
    private HttpStatus status;

    public ApiResponse( String message, T data,HttpStatus httpStatus) {

        this.message = message;
        this.data = data;
        this.status = httpStatus;
    }

}
