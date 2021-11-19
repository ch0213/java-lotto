package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class GradeTest {

    @DisplayName("일치하는 개수에 맞게 등급을 반환한다.")
    @ParameterizedTest(name = "[{index}] matchCount: {0}, expected: {1}")
    @CsvSource(value = {
            "1000, BANG",
            "6, FIRST",
            "5, SECOND",
            "4, THIRD",
            "3, FOURTH",
            "2, BANG",
            "0, BANG",
            "-1, BANG"
    })
    void from(int matchCount, Grade expectedGrade) {
        assertThat(Grade.from(matchCount)).isEqualTo(expectedGrade);
    }
    
    
    @DisplayName("List를 Map으로 잘 변환하는지")
    @Test
    void mapOf() {
        Map<Grade, Long> grades = Grade.mapOf(asList(Grade.BANG, Grade.FIRST, Grade.THIRD, Grade.THIRD, Grade.FIRST));
        assertThat(grades).containsOnly(
                entry(Grade.FIRST, 2L),
                entry(Grade.SECOND, 0L),
                entry(Grade.THIRD, 2L),
                entry(Grade.FOURTH, 0L)
        );
    }

}
