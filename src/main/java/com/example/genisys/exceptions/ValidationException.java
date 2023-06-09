package com.example.genisys.exceptions;

import com.example.genisys.dto.ResponseDto;
import com.example.genisys.enums.ResponseCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ValidationException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String responseDescription = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        ResponseDto response = new ResponseDto(ResponseCodes.ERROR.getCode(), responseDescription);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }
}
