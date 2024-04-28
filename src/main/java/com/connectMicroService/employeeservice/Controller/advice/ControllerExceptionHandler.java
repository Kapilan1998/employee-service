package com.connectMicroService.employeeservice.Controller.advice;

import com.connectMicroService.employeeservice.Dto.ResponseMessageDto;
import com.connectMicroService.employeeservice.Exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(Exception ex) {
        log.error("Internal server error: ", ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", List.of("Internal Service Exception"));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<Object> handleServiceException(ServiceException ex) {
        log.error("Internal service error: ", ex);
        ApiError apiError = new ApiError(ex.getHttpStatus().value(), ex.getHeaderMessage(), List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, ex.getHttpStatus());
    }

    @ExceptionHandler(value = {FeignException.class})
    protected ResponseEntity<Object> handleFeignException(FeignException ex) {
        log.error("Feign client execution error: ", ex);
        ApiError apiError = translate(ex);
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        List<String> errorMessages = new ArrayList<>();
        for (ConstraintViolation<?> violation : violations) {
            errorMessages.add(violation.getMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),"Bad request",errorMessages);
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    private ApiError translate(FeignException ex) {
        try {
            int i = ex.getMessage().indexOf("{\"status");
            String substring = ex.getMessage().substring(i, ex.getMessage().length() - 1);
            return objectMapper.readValue(substring, ApiError.class);
        } catch (Exception e) {
            return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", List.of("Internal Service Exception"));
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        log.error("Bad request: ", ex);
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format(fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad request", errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ApiError {
        private int status;
        private String message;
        private List<String> errors;
    }

//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ResponseMessageDto handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
//        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
//        responseMessageDto.setMessage("Maximum upload size exceeded. Please upload files smaller than 500MB.");
//        return responseMessageDto;
//    }

}

