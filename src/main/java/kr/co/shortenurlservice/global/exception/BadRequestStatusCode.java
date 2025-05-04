package kr.co.shortenurlservice.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BadRequestStatusCode {

    BAD_REQUEST_INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "4000001", "요청한 파라미터가 유효하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
