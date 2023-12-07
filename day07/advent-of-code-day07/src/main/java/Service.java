import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Service {

    public List<Cards> games = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(" ");
                char[] cards = datas[0].toCharArray();
                games.add(new Cards(cards, Integer.parseInt(datas[1])
                ));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    public int partOne() {
        games.sort(Comparator.comparing(Cards::getRank));
        for (Cards card: games ) {
            System.out.println(card.getRank());
        }
        return games.stream().mapToInt(value -> value.getBid() * value.getRank())
                .sum();
        //1272011 low
    }

    public int partTwo() {
        return 0;
    }

}
