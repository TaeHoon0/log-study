package kr.co.shortenurlservice.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotFoundStatusCode {

    NOT_FOUND_SHORTEN_URL(HttpStatus.NOT_FOUND, "4040001", "존재하지 않는 단축 url 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
