package com.landon.hcrawler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CrawlerSuccessResponseDto {

    @JsonProperty("Status")
    private final int status;

    @JsonProperty("Merge")
    private final String merge;
}
