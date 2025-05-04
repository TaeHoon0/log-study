package kr.co.shortenurlservice.domain.exception;

import lombok.Getter;

@Getter
public class NotFoundShortenUrlException extends RuntimeException {

    String shortenUrlKey;

    public NotFoundShortenUrlException(String shortenUrlKey) {
        this.shortenUrlKey = shortenUrlKey;
    }
}
