package kr.co.shortenurlservice.application.service;

import kr.co.shortenurlservice.domain.exception.LackOfShortenUrlKeyException;
import kr.co.shortenurlservice.domain.exception.NotFoundShortenUrlException;
import kr.co.shortenurlservice.domain.entity.ShortenUrl;
import kr.co.shortenurlservice.domain.repository.ShortenUrlRepository;
import kr.co.shortenurlservice.global.exception.BadRequestStatusCode;
import kr.co.shortenurlservice.global.exception.NotFoundStatusCode;
import kr.co.shortenurlservice.presentation.dto.request.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.dto.response.ShortenUrlCreateResponseDto;
import kr.co.shortenurlservice.presentation.dto.ShortenUrlInformationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SimpleShortenUrlService {

    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    SimpleShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    /**
     * 단축 url 생성 후 기존 url mapping
     */
    public ShortenUrlCreateResponseDto generateShortenUrl(
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto
    ) {

        String originalUrl = shortenUrlCreateRequestDto.getOriginalUrl();
        String shortenUrlKey = getUniqueShortenUrlKey();

        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);
        shortenUrlRepository.saveShortenUrl(shortenUrl);

        log.info("shortenUrl 생성 : {}", shortenUrl.getShortenUrlKey());

        return new ShortenUrlCreateResponseDto(shortenUrl);
    }

    /**
     * 단축 url key 로 기존 URL 조회, 조회 때마다 조회 수 증가
     */
    public String getOriginalUrlByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

        if(null == shortenUrl)
            throw new NotFoundShortenUrlException(shortenUrlKey);

        shortenUrl.increaseRedirectCount();
        shortenUrlRepository.saveShortenUrl(shortenUrl);

        return shortenUrl.getOriginalUrl();
    }

    /**
     * 단축 url key로 정보 조회
     */
    public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

        if(null == shortenUrl)
            throw new NotFoundShortenUrlException(shortenUrlKey);

        return new ShortenUrlInformationDto(shortenUrl);
    }

    /**
     * 모든 단축 url key 정보 조회
     */
    public List<ShortenUrlInformationDto> getAllShortenUrlInformationDto() {
        List<ShortenUrl> shortenUrls = shortenUrlRepository.findAll();

        return shortenUrls
                .stream()
                .map(shortenUrl -> new ShortenUrlInformationDto(shortenUrl))
                .toList();
    }


    // 단축 url key 생성
    private String getUniqueShortenUrlKey() {
        final int MAX_RETRY_COUNT = 5;
        int count = 0;

        while(count++ < MAX_RETRY_COUNT) {
            String shortenUrlKey = ShortenUrl.generateShortenUrlKey();
            ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

            if(null == shortenUrl)
                return shortenUrlKey;
        }

        throw new LackOfShortenUrlKeyException();
    }

}
