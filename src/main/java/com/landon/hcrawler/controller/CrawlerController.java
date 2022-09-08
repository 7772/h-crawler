package com.landon.hcrawler.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.landon.hcrawler.dto.CrawlerSuccessResponseDto;
import com.landon.hcrawler.service.CrawlerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlerService crawlerService;

    @GetMapping("/")
    public ResponseEntity<CrawlerSuccessResponseDto> crawl() {
        return ResponseEntity.ok(crawlerService.crawl());
    }
}
