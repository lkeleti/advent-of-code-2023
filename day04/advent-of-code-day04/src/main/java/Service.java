import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {

    List<ScratchCard> scratchCards = new ArrayList<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(":");
                String name = datas[0];
                String numbers = datas[1];
                String winnerNumbers = numbers.split("\\|")[0];
                String chosenNumbers = numbers.split("\\|")[1];

                List<Integer> winnerNumbersList = new ArrayList<>();
                for (String n: winnerNumbers.split(" ")) {
                    if (!n.isEmpty()) {
                        winnerNumbersList.add(Integer.parseInt(n));
                    }
                }

                List<Integer> chosenNumbersList = new ArrayList<>();
                for (String n: chosenNumbers.split(" ")) {
                    if (!n.isEmpty()) {
                        chosenNumbersList.add(Integer.parseInt(n));
                    }
                }

                scratchCards.add(
                        new ScratchCard(name, winnerNumbersList, chosenNumbersList)
                );

            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    public int partOne() {
        int sum = 0;
        for (ScratchCard scratchCard: scratchCards) {
            int point = 0;
            for (int number: scratchCard.getWinnerNumbers()) {
                if (scratchCard.getChosenNumber().contains(number)) {
                    if (point == 0) {
                        point = 1;
                    } else {
                        point = point * 2;
                    }
                }
            }
            sum += point;
        }
        return sum;
    }

    public int partTwo() {
        return 0;
    }
}
