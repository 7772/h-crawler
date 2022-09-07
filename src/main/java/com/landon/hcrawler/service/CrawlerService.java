package com.landon.hcrawler.service;

import org.springframework.stereotype.Service;

import com.landon.hcrawler.dto.CrawlerResponseDto;

@Service
public class CrawlerService {

    public CrawlerResponseDto crawl() {
        return new CrawlerResponseDto(200, "TEST");
    }
}
