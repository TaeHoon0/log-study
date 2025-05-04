package kr.co.shortenurlservice.global.exception;

import java.util.List;
import kr.co.shortenurlservice.domain.exception.LackOfShortenUrlKeyException;
import kr.co.shortenurlservice.domain.exception.NotFoundShortenUrlException;
import kr.co.shortenurlservice.presentation.dto.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LackOfShortenUrlKeyException.class)
    public ResponseEntity<ApiResult> handleLackOfShortenUrlKeyException(
        LackOfShortenUrlKeyException ex
    ) {

        log.error(InternalServerErrorStatusCode.LACK_OF_SHORTEN_URL.getMessage());
        //TODO Slack 알림 보내기

        return ResponseEntity.status(InternalServerErrorStatusCode.LACK_OF_SHORTEN_URL.getHttpStatus())
            .body(
                ApiResult.builder()
                    .code(InternalServerErrorStatusCode.LACK_OF_SHORTEN_URL.getCode())
                    .message(InternalServerErrorStatusCode.LACK_OF_SHORTEN_URL.getMessage())
                    .build()
            );
    }

    @ExceptionHandler(NotFoundShortenUrlException.class)
    public ResponseEntity<ApiResult> handleNotFoundShortenUrlException(
        NotFoundShortenUrlException ex
    ) {

        log.debug("단축 url key를 찾지 못 했습니다. key : {}", ex.getShortenUrlKey());

        return ResponseEntity.status(NotFoundStatusCode.NOT_FOUND_SHORTEN_URL.getHttpStatus())
            .body(
                ApiResult.builder()
                    .code(NotFoundStatusCode.NOT_FOUND_SHORTEN_URL.getCode())
                    .message(NotFoundStatusCode.NOT_FOUND_SHORTEN_URL.getMessage())
                    .build()
            );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException ex
    ) {

        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> String.format("%s : %s", error.getField(), error.getDefaultMessage()))
            .toList();

        return ResponseEntity.status(BadRequestStatusCode.BAD_REQUEST_INVALID_PARAMETER.getHttpStatus())
            .body(
                ApiResult.builder()
                    .code(BadRequestStatusCode.BAD_REQUEST_INVALID_PARAMETER.getCode())
                    .message(BadRequestStatusCode.BAD_REQUEST_INVALID_PARAMETER.getMessage())
                    .result(errors)
                    .build()
            );
    }
}
