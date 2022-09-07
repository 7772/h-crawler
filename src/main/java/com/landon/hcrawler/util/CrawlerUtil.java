package com.landon.hcrawler.util;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.jsoup.Jsoup;
import lombok.extern.slf4j.Slf4j;

import com.landon.hcrawler.exception.CrawlerTimeoutException;

@Slf4j
public class CrawlerUtil {
    private static final int TIMEOUT_MILLIS = 3000;

    /**
     *
     * @param url - 파싱 처리할 URL 문자열
     * @return 파싱 처리된 문자열
     * @throws IOException 파싱 처리 시 Exception 발생한 경우
     * @throws CrawlerTimeoutException Connection Timeout (3000ms) 발생한 경우
     */
    public static String crawl(String url) throws CrawlerTimeoutException, IOException {
        try {
            return Jsoup.parse(new URL(url), TIMEOUT_MILLIS).text();
        } catch (SocketTimeoutException exception) {
            throw new CrawlerTimeoutException(exception.getMessage(), exception);
        }
    }
}
