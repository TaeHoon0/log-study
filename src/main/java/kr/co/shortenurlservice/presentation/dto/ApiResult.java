package kr.co.shortenurlservice.presentation.dto;

import kr.co.shortenurlservice.global.exception.SuccessStatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiResult<T> {

    private final String code;
    private final String message;
    private final T result;

    public static <T> ApiResult<T> ok() {

        return ApiResult.<T>builder()
                .code(SuccessStatusCode.OK.getCode())
                .message(SuccessStatusCode.OK.getMessage())
                .result(null)
                .build();
    }

    public static <T> ApiResult<T> ok(T result) {

        return ApiResult.<T>builder()
                .code(SuccessStatusCode.OK.getCode())
                .message(SuccessStatusCode.OK.getMessage())
                .result(result)
                .build();
    }
}
