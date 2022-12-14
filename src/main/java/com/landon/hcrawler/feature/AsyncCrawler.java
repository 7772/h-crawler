package com.landon.hcrawler.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.landon.hcrawler.util.CrawlerUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * AsyncCrawler
 *
 * 비동기로 데이터를 크롤링하는 역할을 담당하는 클래스
 */
@Slf4j
@Component
public class AsyncCrawler {

    /**
     * Thread Executor는 전달된 URL의 size 만큼 생성한다.
     *
     * @param urls - 크롤링할 URL을 담은 리스트
     * @return List<String> - 각 URL을 크롤링한 결과 리스트
     */
    public List<String> run(List<String> urls) {
        List<CompletableFuture<Void>> crawlRequests = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(urls.size());

        List<String> results = new ArrayList<>();

        for (String url : urls) {
            crawlRequests.add(
                CompletableFuture
                    .supplyAsync(() -> {
                            log.info("Start Crawling - URL: {}", url);
                            return CrawlerUtil.crawl(url);
                        }, executorService)
                    .thenAccept(results::add)
            );
        }

        CompletableFuture.allOf(
            (crawlRequests).toArray(new CompletableFuture[crawlRequests.size()])
        ).join();

        return results;
    }
}
