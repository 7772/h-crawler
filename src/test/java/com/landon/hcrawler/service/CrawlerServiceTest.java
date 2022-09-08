package com.landon.hcrawler.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.landon.hcrawler.dto.CrawlerResponseDto;
import com.landon.hcrawler.feature.AsyncCrawler;
import com.landon.hcrawler.feature.HSitesCollector;

@SpringBootTest(classes = {
    CrawlerService.class,
    HSitesCollector.class,
    AsyncCrawler.class
})
public class CrawlerServiceTest {

    @Autowired
    private CrawlerService crawlerService;

    @MockBean
    private HSitesCollector hSitesCollector;

    @Test
    @DisplayName("CrawlerResponseDto 인스턴스를 반환할 수 있다.")
    public void testSuccessCase() {
        // Given
        given(hSitesCollector.collectAndMerge()).willReturn("mergeresult123");

        // When
        CrawlerResponseDto result = crawlerService.crawl();

        // Then
        assertNotNull(result);
    }
}
