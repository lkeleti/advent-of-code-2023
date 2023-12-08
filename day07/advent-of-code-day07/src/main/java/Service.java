import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {

    public List<Cards> games = new ArrayList<>();
    public List<CardsWithJoker> gamesWithJokers = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(" ");
                char[] cards = datas[0].toCharArray();
                games.add(new Cards(cards, Integer.parseInt(datas[1])
                ));

                gamesWithJokers.add(new CardsWithJoker(cards, Integer.parseInt(datas[1])
                ));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    public int partOne() {
        Collections.sort(games);

        int sum = 0;
        for (int i = 0; i < games.size(); i++) {
            sum += ((i + 1) * games.get(i).getBid());
        }
        return sum;
    }

    public int partTwo() {
        Collections.sort(gamesWithJokers);

        int sum = 0;
        for (int i = 0; i < gamesWithJokers.size(); i++) {
            sum += ((i + 1) * gamesWithJokers.get(i).getBid());
        }
        return sum;
        //252064423 low
    }

}
