package kr.co.shortenurlservice.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum InternalServerErrorStatusCode {

    LACK_OF_SHORTEN_URL(HttpStatus.INTERNAL_SERVER_ERROR, "5000001", "단축 url이 모두 소진되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
