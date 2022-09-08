package com.landon.hcrawler.config;

import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@Getter
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum CacheType {
    @FieldNameConstants.Include
    H_SITE_CRAWLING_RESULT(1, 1000),
    ;

    private final int expireMinutes;
    private final int maximumSize;

    CacheType(int expireMinutes, int maximumSize) {
        this.expireMinutes = expireMinutes;
        this.maximumSize = maximumSize;
    }
}
