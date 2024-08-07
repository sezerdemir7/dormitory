package org.demir.dormitory.exception;


import org.demir.dormitory.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ApiResponse<String> handleNotFoundException(NotFoundException ex) {
        return new ApiResponse<>( ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ApiResponse<String> handleBadRequestException(BadRequestException ex) {
        return new ApiResponse<>( ex.getMessage(), null,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse<String> handleException(Exception ex) {
        return new ApiResponse<>( "Internal Server Error", null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
