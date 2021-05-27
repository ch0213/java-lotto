package lotto.domain;

import java.util.Arrays;
import java.util.Collections;

public enum LottoRank {

    LOSE(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int matchCount;
    private final boolean matchBonusTarget;
    private final long winAmount;

    LottoRank(int matchCount, boolean matchBonusTarget, int winAmount) {
        this.matchCount = matchCount;
        this.matchBonusTarget = matchBonusTarget;
        this.winAmount = winAmount;
    }

    public static LottoRank of(int matchCount, boolean matchBonusTarget) {
        if (isLose(matchCount)) {
            return LOSE;
        }
        return Arrays.stream(values())
                .sorted(Collections.reverseOrder())
                .filter(rank -> rank.match(matchCount, matchBonusTarget))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("매칭 숫자에 해당하는 등수가 존재하지 않습니다."));
    }

    private boolean match(int matchCount, boolean matchBonusTarget) {
        if (this.matchBonusTarget) {
            return isMatch(matchCount) && matchBonusTarget;
        }
        return isMatch(matchCount);
    }

    private boolean isMatch(int matchCount) {
        return this.matchCount == matchCount;
    }

    private static boolean isLose(int matchCount) {
        return matchCount >= LOSE.isMatch() && matchCount < FIFTH.isMatch();
    }

    public int isMatch() {
        return matchCount;
    }

    public boolean isMatchBonusTarget() {
        return matchBonusTarget;
    }

    public long winAmount() {
        return winAmount;
    }
}