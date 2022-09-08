package com.landon.hcrawler.feature;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * HSitesCollector
 *
 * 3가지 사이트의 데이터를 수집, 머지하는 역할을 담당하는 클래스
 */
@Component
@RequiredArgsConstructor
public class HSitesCollector {

    private static final String SHOP_HYUNDAI = "https://shop.hyundai.com";
    private static final String KIA = "https://www.kia.com";
    private static final String GENESIS = "https://www.genesis.com";

    private final AsyncCrawler asyncCrawler;

    public String collectAndMerge() {
        List<String> siteCollectResults = asyncCrawler.run(
            Arrays.asList(SHOP_HYUNDAI, KIA, GENESIS)
        );

        StringBuilder result = new StringBuilder();

        for (String siteCollectResult : siteCollectResults) {
            result.append(siteCollectResult);
        }

        return result.toString();
    }
}
