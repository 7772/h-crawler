package com.landon.hcrawler.exception;

import java.io.IOException;

public class CrawlerIOException extends RuntimeException {

    public CrawlerIOException(String message, IOException exception) {
        super(message, exception);
    }
}
