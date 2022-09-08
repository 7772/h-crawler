package com.landon.hcrawler.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.landon.hcrawler.config.CacheType.Fields;
import com.landon.hcrawler.dto.CrawlerResponseDto;
import com.landon.hcrawler.feature.HSitesCollector;
import com.landon.hcrawler.feature.HSitesDataProcessor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlerService {

    private final HSitesCollector hSitesCollector;

    @Cacheable(cacheNames = Fields.H_SITE_CRAWLING_RESULT)
    public CrawlerResponseDto crawl() {
        String result = new HSitesDataProcessor(hSitesCollector.collectAndMerge())
            .parseOnlyAlphanumeric()
            .distinctAndSortByAsc()
            .cross()
            .getData();

        return new CrawlerResponseDto(200, result);
    }
}
