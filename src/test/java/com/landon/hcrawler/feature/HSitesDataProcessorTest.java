package com.landon.hcrawler.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HSitesDataProcessorTest {

    @Test
    @DisplayName("parseOnlyAlphanumeric - 영문, 숫자만 존재하도록 데이터를 파싱할 수 있다.")
    public void testParseOnlyAlphanumeric() {
        // Given
        String hSitesData = "가152나다AF라A마Avbw2314";
        HSitesDataProcessor processor = new HSitesDataProcessor(hSitesData);

        // When
        String result = processor
            .parseOnlyAlphanumeric()
            .getData();

        // Then
        assertEquals("152AFAAvbw2314", result);
    }

    @Test
    @DisplayName("parseOnlyAlphanumeric - 데이터가 null 인 경우 아무 처리도 하지 않는다.")
    public void testParseOnlyAlphanumericNull() {
        // Given
        HSitesDataProcessor processor = new HSitesDataProcessor(null);

        // When
        String result = processor
            .parseOnlyAlphanumeric()
            .getData();

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("distinct - 중복된 문자를 제거하고, 오름차순 정렬할 수 있다.")
    public void testDistinctAndSortByAsc() {
        // Given
        String hSitesData = "html124divABCDefgtaBlelmg1";
        HSitesDataProcessor processor = new HSitesDataProcessor(hSitesData);

        // When
        String result = processor
            .distinctAndSortByAsc()
            .getData();

        // Then
        assertEquals("AaBCDdefghilmtv124", result);
    }

    @Test
    @DisplayName("distinct - 데이터가 null 인 경우 아무 처리도 하지 않는다.")
    public void testDistinctAndSortByAscNull() {
        // Given
        HSitesDataProcessor processor = new HSitesDataProcessor(null);

        // When
        String result = processor
            .distinctAndSortByAsc()
            .getData();

        // Then
        assertNull(result);
    }
}
