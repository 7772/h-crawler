package com.landon.hcrawler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CrawlerErrorResponseDto {

    @JsonProperty("Status")
    private final int status;

    @JsonProperty("Error")
    private final String error;
}
