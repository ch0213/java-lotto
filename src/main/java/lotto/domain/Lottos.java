package lotto.domain;

import java.util.*;

import static lotto.utils.Constants.NUMBER_ZERO;

public class Lottos {
    private DefaultLottoNumbers defaultLottoNumbers;
    private List<LottoGame> lottoGames;

    public Lottos() {
        this.lottoGames = new ArrayList<>();
        this.defaultLottoNumbers = new DefaultLottoNumbers();
    }

    public Lottos(List<LottoGame> lottoGames) {
        this.lottoGames = lottoGames;
    }

    public void makeLottos(LottoGameCount lottoGameCount) {
        for (int i = NUMBER_ZERO; i < lottoGameCount.getLottoAutoCount(); i++) {
            lottoGames.add(new LottoGame(new LottoNumbers().createAutoLottoNumbers(defaultLottoNumbers)));
        }
    }

    public void makeManualLottos(List<String[]> inputLottos) {
        inputLottos.forEach(inputLotto -> {
            lottoGames.add(new LottoGame(new LottoNumbers().createManualLottoNumbers(Arrays.asList(inputLotto))));
        });
    }

    public Long winRankLottoCount(Long rankCount) {
        return lottoGames.stream()
                .filter(lottoGame -> lottoGame.getMatchedCount().equals(rankCount))
                .count();
    }

    public List<LottoGame> getLottoGames() {
        return Collections.unmodifiableList(lottoGames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos = (Lottos) o;
        return Objects.equals(defaultLottoNumbers, lottos.defaultLottoNumbers) &&
                Objects.equals(lottoGames, lottos.lottoGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultLottoNumbers, lottoGames);
    }
}