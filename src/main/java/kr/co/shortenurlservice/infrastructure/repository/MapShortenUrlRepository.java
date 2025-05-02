package kr.co.shortenurlservice.infrastructure.repository;

import kr.co.shortenurlservice.domain.entity.ShortenUrl;
import kr.co.shortenurlservice.domain.repository.ShortenUrlRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapShortenUrlRepository implements ShortenUrlRepository {

    private Map<String, ShortenUrl> shortenUrls = new ConcurrentHashMap<>();

    @Override
    public void saveShortenUrl(ShortenUrl shortenUrl) {
        shortenUrls.put(shortenUrl.getShortenUrlKey(), shortenUrl);
    }

    @Override
    public ShortenUrl findShortenUrlByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrls.get(shortenUrlKey);
        return shortenUrl;
    }

    @Override
    public List<ShortenUrl> findAll() {
        return new ArrayList<>(shortenUrls.values());
    }

}
