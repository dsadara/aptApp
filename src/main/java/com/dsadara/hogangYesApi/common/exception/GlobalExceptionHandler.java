package com.dsadara.hogangYesApi.common.exception;

import com.dsadara.hogangYesApi.common.dto.ErrorResponse;
import com.dsadara.hogangYesApi.realestate.exception.RealEstateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.dsadara.hogangYesApi.common.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.dsadara.hogangYesApi.common.type.ErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RealEstateException.class)
    public ErrorResponse handleApartmentException(RealEstateException e) {
        log.error("{} is occuurred.", e.getErrorCode());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
    }

    // 아파트를 없는 특징(Feature)으로 검색할때 발생하는 에러
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException is occurred.", e);

        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

    // dev profile(H2 DB) 사용시 mysql 네이티브 쿼리를 사용하는 API를 호출했을때 발생하는 에러
    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ErrorResponse handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e) {
        log.error("InvalidDataAccessResourceUsageException is occurred.", e);

        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception is occurred.", e);
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getDescription());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
