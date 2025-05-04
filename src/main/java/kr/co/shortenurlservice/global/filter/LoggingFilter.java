package kr.co.shortenurlservice.global.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(
        ServletRequest request, ServletResponse response, FilterChain filterChain
    ) throws IOException, ServletException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        long startTime = System.currentTimeMillis();

        try {

            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {

            long duration = System.currentTimeMillis() - startTime;

            log.trace("[Request] {} : {} | Duration : {}ms",
                wrappedRequest.getMethod(), wrappedRequest.getRequestURI(), duration);

            String requestBody = new String(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
            log.trace("[Request Body] {}", requestBody);

            String responseBody = new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);
            log.trace("[Response Body] {}", responseBody);

            wrappedResponse.copyBodyToResponse();
        }
    }
}
