package com.landon.hcrawler.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AsyncCrawler.class)
public class HSiteCollectorTest {

    @Mock
    private AsyncCrawler asyncCrawler;

    @Test
    @DisplayName("SHOP_HYUNDAI, KIA, GENESIS 3가지 사이트를 수집하고 머지할 수 있다.")
    public void testCollectAndMerge() {
        // Given
        List<String> mockResult = Arrays.asList("shop-hyundai", "kia", "genesis");
        given(asyncCrawler.run(any())).willReturn(mockResult);

        HSitesCollector hSiteCollector = new HSitesCollector(asyncCrawler);

        // When
        String result = hSiteCollector.collectAndMerge();

        // Then
        assertEquals(result, "shop-hyundaikiagenesis");
    }
}
