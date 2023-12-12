import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final List<List<Character>> board = new ArrayList<>();
    private final List<Pos> galaxies = new ArrayList<>();
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


    private void expandBoard() {
        List<Integer> rowNumbers = new ArrayList<>();
        List<Integer> colNumbers = new ArrayList<>();

        for (int i =0; i < board.size(); i++) {
            if (board.get(i).stream()
                    .filter(c->c=='.')
                    .count() == board.get(i).size()) {
                rowNumbers.add(i);
            }
        }

        int counter = 0;
        for (int rowNumber: rowNumbers) {
            rowNumber += counter;
            board.add(rowNumber, newSpaceRow());
            counter++;
        }

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

        for (int i = 0; i < board.size(); i++) {
            counter = 0;
            for (int colNumber: colNumbers) {
                board.get(i).add(colNumber + counter, '.');
                counter++;
            }
        }
    }

    private List<Character> newSpaceRow() {
        List<Character> emptySpace = new ArrayList<>();
        for (int i = 0; i < board.getFirst().size(); i++) {
            emptySpace.add('.');
        }
        return emptySpace;
    }

    private void findGalaxies() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.getFirst().size(); j++) {
                if (board.get(i).get(j) == '#') {
                    galaxies.add(new Pos(j,i));
                }
            }
        }
    }

    private int calcAllDistances() {
        int total = 0;
        for (int i = 0; i < galaxies.size()-1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                total += galaxies.get(i).calcDistance(galaxies.get(j));
            }
        }
        return total;
    }
    public long partOne() {
        expandBoard();
        findGalaxies();
        return calcAllDistances();
    }

    public long partTwo() {
        return 0;
    }
}
