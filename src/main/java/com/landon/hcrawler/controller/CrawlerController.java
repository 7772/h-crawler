package com.landon.hcrawler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.landon.hcrawler.dto.CrawlerResponseDto;

@RestController
public class CrawlerController {
    @GetMapping("/")
    public CrawlerResponseDto crawl() {
        return new CrawlerResponseDto(200, "TEST");
    }
}
