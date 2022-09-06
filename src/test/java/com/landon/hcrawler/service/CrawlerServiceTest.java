package com.landon.hcrawler.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.landon.hcrawler.dto.CrawlerResponseDto;

@SpringBootTest(classes = CrawlerService.class)
public class CrawlerServiceTest {
    @Autowired
    private CrawlerService crawlerService;

    @Test
    @DisplayName("CrawlerResponseDto 인스턴스를 반환할 수 있다.")
    public void testSuccessCase() {
        // When
        CrawlerResponseDto result = crawlerService.crawl();

        // Then
        assertNotNull(result);
    }
}
