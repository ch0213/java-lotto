package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.RandomNumberGenerator;
import lotto.domain.Ranking;
import lotto.service.LottoService;
import lotto.util.YieldCalculator;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.Map;

public class LottoController {

    private static final int EACH_PRICE = 1000;
    LottoService lottoService = new LottoService();

    public void startLotto() {
        int purchasePrice = InputView.inputPurchaseAmount();
        Lottos registerLotts = lottoService.generateRandomLottos(new RandomNumberGenerator(), purchasePrice, EACH_PRICE);
        ResultView.printPurchaseMessage(registerLotts, purchasePrice / EACH_PRICE);

        String lastWeekWinningNumber = InputView.inputLastWeekWinningNumber();
        String bonusNumber = InputView.inputLastWeekBonusNumber();
        Map<Ranking, Integer> winningMap = lottoService.registerWinningNumbers(lastWeekWinningNumber, bonusNumber);
        ResultView.printWinningMessage(winningMap, YieldCalculator.calculate(winningMap, purchasePrice - purchasePrice % EACH_PRICE));
    }
}
