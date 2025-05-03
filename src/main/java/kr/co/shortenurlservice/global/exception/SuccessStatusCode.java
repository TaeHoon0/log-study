package kr.co.shortenurlservice.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatusCode {

    OK(HttpStatus.OK, "2000000", "요청이 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
