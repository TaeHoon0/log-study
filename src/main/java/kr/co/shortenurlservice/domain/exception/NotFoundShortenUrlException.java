package kr.co.shortenurlservice.domain.exception;

import kr.co.shortenurlservice.global.exception.BadRequestStatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotFoundShortenUrlException extends RuntimeException {

    private BadRequestStatusCode badRequestStatusCode;

    public NotFoundShortenUrlException(BadRequestStatusCode badRequestStatusCode) {
        this.badRequestStatusCode = badRequestStatusCode;
    }
}
