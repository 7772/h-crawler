package com.landon.hcrawler.feature;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HSitesDataProcessor {

    private String hSitesData;

    private static final String REGEX_ONLY_ALPHANUMERIC = "[^A-Za-z0-9]";

    /**
     * 영문, 숫자만 존재하도록 파싱하는 함수
     * @return HSitesDataProcessor
     */
    public HSitesDataProcessor parseOnlyAlphanumeric() {
        if (hSitesData != null) {
            this.hSitesData = hSitesData.replaceAll(REGEX_ONLY_ALPHANUMERIC, "");
        }

        return this;
    }

    /**
     * 중복된 문자를 제거하고, 오름차순으로 정렬하는 함수
     * @return HSitesDataProcessor
     */
    public HSitesDataProcessor distinctAndSortByAsc() {
        if (hSitesData != null) {
            this.hSitesData = hSitesData.chars()
                .distinct()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        }

        return this;
    }

    /**
     * 아래 조건에 맞추어 데이터를 교차하는 함수
     * - 대문자소문자숫자대문자소문자숫자...
     * - 교차 진행 시 교차될 영문 데이터가 없다면 남아있는 숫자로만 정렬되어야 함
     * - 반대로 교차될 숫자가 없다면 남아있는 영문으로만 정렬해야 함
     * @return HSitesDataProcessor
     */
    public HSitesDataProcessor cross() {
        return this;
    }

    public String getData() {
        return hSitesData;
    }
}
