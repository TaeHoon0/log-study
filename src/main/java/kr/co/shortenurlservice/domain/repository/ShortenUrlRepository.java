package kr.co.shortenurlservice.domain.repository;

import kr.co.shortenurlservice.domain.entity.ShortenUrl;

import java.util.List;

public interface ShortenUrlRepository {
    void saveShortenUrl(ShortenUrl shortenUrl);
    ShortenUrl findShortenUrlByShortenUrlKey(String shortenUrlKey);

    List<ShortenUrl> findAll();
}
