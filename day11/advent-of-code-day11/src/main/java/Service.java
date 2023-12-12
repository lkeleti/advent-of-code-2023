import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final List<List<Character>> board = new ArrayList<>();
    private final List<Pos> galaxies = new ArrayList<>();
    private final List<Integer> rowNumbers = new ArrayList<>();
    private final List<Integer> colNumbers = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                char[] datas = line.toCharArray();
                List<Character> row = new ArrayList<>();
                for (char c: datas) {
                    row.add(c);
                }
                board.add(row);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private void findEmptySpaces() {
        for (int i = 0; i < board.getFirst().size(); i++) {
            boolean isSpace = true;
            for (List<Character> characters : board) {
                if (characters.get(i) != '.') {
                    isSpace = false;
                }
            }
            if (isSpace) {
                colNumbers.add(i);
            }
        }

        for (int i =0; i < board.size(); i++) {
            if (board.get(i).stream()
                    .filter(c->c=='.')
                    .count() == board.get(i).size()) {
                rowNumbers.add(i);
            }
        }
    }

    private void findGalaxies(int expandValue) {
        galaxies.clear();
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.getFirst().size(); j++) {
                if (board.get(i).get(j) == '#') {
                    Pos newPos = rebaseByExpand(j,i, expandValue);
                    galaxies.add(newPos);
                }
            }
        }
    }

    private Pos rebaseByExpand(int col, int row, int expandValue) {
        int colCounter = 0;
        int rowCounter = 0;
        for (int rowNumber : rowNumbers) {
            if (rowNumber < row) {
                rowCounter++;
            }
        }
        for (int colNumber : colNumbers) {
            if (colNumber < col) {
                colCounter++;
            }
        }
        return new Pos(col + (colCounter * expandValue), row + (rowCounter * expandValue));
    }

    private long calcAllDistances() {
        long total = 0;
        for (int i = 0; i < galaxies.size()-1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                total += galaxies.get(i).calcDistance(galaxies.get(j));
            }
        }
        return total;
    }
    public long partOne() {
        findEmptySpaces();
        findGalaxies(1);
        return calcAllDistances();
        //9647174
    }

    public long partTwo() {
        findGalaxies(1000000-1);
        return calcAllDistances();
    }
}
