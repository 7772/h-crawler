package com.landon.hcrawler.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.landon.hcrawler.dto.CrawlerResponseDto;
import com.landon.hcrawler.exception.CrawlerTimeoutException;
import com.landon.hcrawler.service.CrawlerService;

@WebMvcTest(CrawlerController.class)
public class CrawlerControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CrawlerService crawlerService;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .build();
    }

    @Test
    @DisplayName("API 요청이 성공할 수 있다.")
    public void testSuccessCase() throws Exception {
        // Given
        given(crawlerService.crawl())
            .willReturn(new CrawlerResponseDto(200, "TEST"));

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.Status").value(200))
            .andExpect(jsonPath("$.Merge").value("TEST"));
    }

    @Test
    @DisplayName("API 요청 실패 시, Status 코드와 에러 메세지를 반환할 수 있다.")
    public void testFailureCase() throws Exception {
        // Given
        given(crawlerService.crawl())
            .willThrow(CrawlerTimeoutException.class);

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.Status").value(HttpStatus.UNPROCESSABLE_ENTITY.value()));
    }
}
