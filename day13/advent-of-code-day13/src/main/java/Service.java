import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    public void readInput(Path path) {

        List<Board> boards = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            List<List<Character>> rows = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    boards.add(new Board(rows));
                    rows.clear();
                } else {
                    List<Character> row = new ArrayList<>();
                    for (Character c : line.toCharArray()) {
                        row.add(c);
                    }
                    rows.add(row);
                }
            }
            boards.add(new Board(rows));
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    public int partOne() {
        return 0;
    }

    public int partTwo() {
        return 0;
    }
}
