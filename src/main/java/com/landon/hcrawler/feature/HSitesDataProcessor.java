package com.landon.hcrawler.feature;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class HSitesDataProcessor {

    private String hSitesData;

    private static final String REGEX_ONLY_ALPHANUMERIC = "[^A-Za-z0-9]";
    private static final Pattern PATTERN_NUMERIC = Pattern.compile("^\\d+");

    /**
     * 영문, 숫자만 존재하도록 파싱하는 함수
     * @return HSitesDataProcessor
     */
    public HSitesDataProcessor parseOnlyAlphanumeric() {
        if (hSitesData == null) {
            return this;
        }

        this.hSitesData = hSitesData.replaceAll(REGEX_ONLY_ALPHANUMERIC, "");

        return this;
    }

    /**
     * 중복된 문자를 제거하고, 특정한 규칙이 적용된 오름차순으로 정렬하는 함수
     * @return HSitesDataProcessor
     */
    public HSitesDataProcessor distinctAndSortByAsc() {
        if (hSitesData == null) {
            return this;
        }

        this.hSitesData = hSitesData.chars()
            .distinct()
            .mapToObj(Character::toString)
            .sorted((a, b) -> {
                Matcher matcherA = PATTERN_NUMERIC.matcher(a);
                Matcher matcherB = PATTERN_NUMERIC.matcher(b);

                boolean isNumericA = matcherA.find();
                boolean isNumericB = matcherB.find();

                if (!isNumericA) {
                    if (isNumericB) {
                        // 숫자를 뒤로
                        return b.compareTo(a);
                    } else {
                        if (a.equalsIgnoreCase(b)) {
                            // a와 b가 같은 알파벳일 경우에는 대소문자 구분
                            return a.compareTo(b);
                        } else {
                            // a와 b가 다른 알파벳일 경우에는 대소문자 구분 X
                            return a.compareToIgnoreCase(b);
                        }
                    }
                } else {
                    if (!isNumericB) {
                        // 숫자를 뒤로
                        return b.compareTo(a);
                    } else {
                        // a와 b가 모두 숫자인 경우
                        Integer number1 = Integer.parseInt(a);
                        Integer number2 = Integer.parseInt(b);

                        return number1.compareTo(number2);
                    }
                }
            })
            .collect(Collectors.joining());

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
        if (hSitesData == null) {
            return this;
        }

        List<Character> onlyUpperCases = hSitesData.chars()
            .filter(Character::isUpperCase)
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());

        List<Character> onlyLowerCases = hSitesData.chars()
            .filter(Character::isLowerCase)
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());

        List<Character> onlyNumbers = hSitesData.chars()
            .filter(Character::isDigit)
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());

        int maxSize = Math.max(onlyUpperCases.size(), Math.max(onlyLowerCases.size(), onlyNumbers.size()));

        StringBuilder result = new StringBuilder();

        Character lastUpperCase = null;
        int lowerCaseIndex = 0;

        for (int i = 0; i < maxSize; i++) {
            boolean isExistUpperCase = onlyUpperCases.size() > i;
            boolean isExistLowerCase = onlyLowerCases.size() > lowerCaseIndex;
            boolean isExistNumber = onlyNumbers.size() > i;

            if (isExistUpperCase) {
                lastUpperCase = onlyUpperCases.get(i);
                result.append(lastUpperCase);
            }

            if (isExistLowerCase) {
                boolean isBiggerThanLastUpperCase = lastUpperCase != null && lastUpperCase.toString()
                    .compareToIgnoreCase(onlyLowerCases.get(lowerCaseIndex).toString()) >= 0;

                if (isBiggerThanLastUpperCase) {
                    result.append(onlyLowerCases.get(lowerCaseIndex));
                    lowerCaseIndex++;
                } else {
                    if (i - lowerCaseIndex > 1) {
                        result.append(onlyLowerCases.get(lowerCaseIndex));
                        lowerCaseIndex++;
                    }
                }
            }

            if (isExistNumber) {
                result.append(onlyNumbers.get(i));
            }
        }

        for (int i = lowerCaseIndex; i < onlyLowerCases.size(); i++) {
            result.append(onlyLowerCases.get(i));
        }

        this.hSitesData = result.toString();

        return this;
    }

    public String getData() {
        return hSitesData;
    }
}
