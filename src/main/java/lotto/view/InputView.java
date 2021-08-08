package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputView {

    private static final String MESSAGE_INPUT_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_INPUT_VALUE_INCORRECT = "입력값이 올바르지 않습니다.";
    private static final String NUMBER_SEPARATOR = ",";

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public long getInputAmount() {
        System.out.println(MESSAGE_INPUT_AMOUNT);
        long amount = scanner.nextLong();
        scanner.nextLine();
        return amount;
    }

    public List<Integer> getWinningNumber() {
        System.out.println();
        System.out.println(MESSAGE_INPUT_WINNING_NUMBER);
        String input = scanner.nextLine();

        String[] inputArr = input.split(NUMBER_SEPARATOR);

        return Arrays.stream(inputArr)
                .map(String::trim)
                .map(wrapAsThrowException(Integer::parseInt))
                .collect(Collectors.toList());
    }

    private <T, R> Function<T, R> wrapAsThrowException(Function<T, R> f) {
        return (T r) -> {
            try {
                return f.apply(r);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(MESSAGE_INPUT_VALUE_INCORRECT);
            }
        };
    }
}
