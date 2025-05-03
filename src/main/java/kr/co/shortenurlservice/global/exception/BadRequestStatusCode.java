package kr.co.shortenurlservice.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BadRequestStatusCode {

    BAD_REQUEST_SHORTEN_URL(HttpStatus.BAD_REQUEST, "4000000", "잘못된 단축 url 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
