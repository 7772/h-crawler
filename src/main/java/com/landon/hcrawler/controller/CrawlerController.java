package com.landon.hcrawler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.landon.hcrawler.dto.CrawlerResponseDto;
import com.landon.hcrawler.service.CrawlerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlerService crawlerService;

    @GetMapping("/")
    public CrawlerResponseDto crawl() {
        return crawlerService.crawl();
    }
}
