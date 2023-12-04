import java.util.List;

public class ScratchCard {

    private final String name;
    private final List<Integer> winnerNumbers;
    private final List<Integer> chosenNumber;

    private int cardNumber = 1;

    private int numberOfSame = 0;

    public ScratchCard(String name, List<Integer> winnerNumbers, List<Integer> chosenNumber) {
        this.name = name;
        this.winnerNumbers = winnerNumbers;
        this.chosenNumber = chosenNumber;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getWinnerNumbers() {
        return winnerNumbers;
    }

    public List<Integer> getChosenNumber() {
        return chosenNumber;
    }

    public int getNumberOfSame() {
        return numberOfSame;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void incNumberOfSame() {
        numberOfSame++;
    }

    public void incCardNumber(int number) {
        cardNumber += number;
    }

    public int getPoints() {
        if (numberOfSame > 0) {
            return (int)Math. pow(2, (numberOfSame - 1));
        } else
            return 0;
    }
}
