package com.example.newpos.advisor;

import com.example.newpos.exception.NotFoundException;
import com.example.newpos.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return  new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"ERROR message",e.getMessage()+" Not get"), HttpStatus.NOT_FOUND
        );
    }
}
