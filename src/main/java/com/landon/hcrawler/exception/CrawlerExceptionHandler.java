package com.landon.hcrawler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.landon.hcrawler.dto.CrawlerErrorResponseDto;

@ControllerAdvice
public class CrawlerExceptionHandler {

    @ExceptionHandler(CrawlerIOException.class)
    public ResponseEntity<CrawlerErrorResponseDto> handleIOException(CrawlerIOException exception) {
        return ResponseEntity.internalServerError()
            .body(new CrawlerErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }

    @ExceptionHandler(CrawlerTimeoutException.class)
    public ResponseEntity<CrawlerErrorResponseDto> handleIOException(CrawlerTimeoutException exception) {
        return ResponseEntity.unprocessableEntity()
            .body(new CrawlerErrorResponseDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage()));
    }
}
