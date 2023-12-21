import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final List<List<Character>> board = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (Character c : line.toCharArray()) {
                    row.add(c);
                }
                board.add(row);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private Cord findStart() {
        for (int i = 0; i< board.getFirst().size(); i++) {
            for (int j = 0; j< board.size(); j++) {
                if (board.get(i).get(j) == 'S') {
                    return new Cord(i,j);
                }
            }
        }
        throw new IllegalArgumentException("Cannot find start point!");
    }
    public int partOne() {
        return 0;
    }

    public int partTwo() {
        return 0;
    }

}
