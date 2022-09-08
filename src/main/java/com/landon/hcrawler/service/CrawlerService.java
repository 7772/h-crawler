package com.landon.hcrawler.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.landon.hcrawler.config.CacheType.Fields;
import com.landon.hcrawler.dto.CrawlerSuccessResponseDto;
import com.landon.hcrawler.feature.HSitesCollector;
import com.landon.hcrawler.feature.HSitesDataProcessor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlerService {

    private final HSitesCollector hSitesCollector;

    @Cacheable(cacheNames = Fields.H_SITE_CRAWLING_RESULT)
    public CrawlerSuccessResponseDto crawl() {
        String result = new HSitesDataProcessor(hSitesCollector.collectAndMerge())
            .parseOnlyAlphanumeric()
            .distinctAndSortByAsc()
            .cross()
            .getData();

        return new CrawlerSuccessResponseDto(HttpStatus.OK.value(), result);
    }
}
