package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Award {

    NON_MATCH(Arrays.asList(0, 1, 2), 0),
    MATCH_OF_THREE(Collections.singletonList(3), 5000),
    MATCH_OF_FOUR(Collections.singletonList(4), 50_000),
    MATCH_OF_FIVE(Collections.singletonList(5), 1_500_000),
    MATCH_OF_SIX(Collections.singletonList(6), 2_000_000_000);


    private final List<Integer> matchCounts;
    private final int reward;

    Award(List<Integer> matchCounts, int reward) {
        this.reward = reward;
        this.matchCounts = matchCounts;
    }

    public static Award of(int count) {
        return Arrays.stream(values())
            .filter(award -> award.matchCounts.contains(count))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 갯수입니다."));
    }

    public int getReward() {
        return reward;
    }

    public int getTopMatchCount() {
        return matchCounts.get(0);
    }
}