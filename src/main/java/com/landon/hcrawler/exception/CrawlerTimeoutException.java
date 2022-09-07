package com.landon.hcrawler.exception;

import java.net.SocketTimeoutException;

public class CrawlerTimeoutException extends RuntimeException {
    public CrawlerTimeoutException(String message, SocketTimeoutException exception) {
        super(message, exception);
    }
}
