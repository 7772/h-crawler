package com.landon.hcrawler.util;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.jsoup.Jsoup;

import com.landon.hcrawler.exception.CrawlerIOException;
import com.landon.hcrawler.exception.CrawlerTimeoutException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CrawlerUtil {

    private static final int TIMEOUT_MILLIS = 5000;

    /**
     *
     * @param url - 파싱 처리할 URL 문자열
     * @return 파싱 처리된 문자열
     * @throws CrawlerIOException 파싱 처리 시 IO Exception 발생한 경우
     * @throws CrawlerTimeoutException Connection Timeout (5000ms) 발생한 경우
     */
    public static String crawl(String url) {
        try {
            return Jsoup.parse(new URL(url), TIMEOUT_MILLIS).text();
        } catch (SocketTimeoutException exception) {
            throw new CrawlerTimeoutException("[CrawlerUtil] Timeout Exception (" + TIMEOUT_MILLIS + "ms)", exception);
        } catch (IOException exception) {
            throw new CrawlerIOException("[CrawlerUtil] IO Exception", exception);
        }
    }
}
