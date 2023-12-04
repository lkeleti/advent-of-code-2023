import java.util.List;

public class ScratchCard {

    private String name;
    private List<Integer> winnerNumbers;
    private List<Integer> chosenNumber;

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
}
