import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final List<List<Character>> board = new ArrayList<>();

    private Direction start;
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (char c: line.toCharArray()) {
                    row.add(c);
                }
                board.add(row);
            }
            findStart();
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    private void findStart() {
        for (int i=0; i < board.size(); i++) {
            for (int j = 0; j < board.getFirst().size(); j++) {
                if (board.get(i).get(j).equals('S')) {
                    start = new Direction(j,i);
                    System.out.println(start);
                    break;
                }
            }
        }
    }

    private boolean isValidSign(Character dir, Character defSign, Character nextSign) {
        if (dir == 'd') {
            if (defSign == '|' && (nextSign == 'L' || nextSign == 'J')) {
                return true;
            }
            if (defSign == '7' && (nextSign == 'L' || nextSign == 'J' || nextSign == '|')) {
                return true;
            }

            if (defSign == 'F' && (nextSign == 'L' || nextSign == 'J' || nextSign == '|')) {
                return true;
            }
        }

        if (dir == 'u') {
            if (defSign == '|' && (nextSign == '7' || nextSign == 'F')) {
                return true;
            }

            if (defSign == 'L' && (nextSign == '7' || nextSign == 'F' || nextSign == '|')) {
                return true;
            }

            if (defSign == 'J' && (nextSign == '7' || nextSign == 'F' || nextSign == '|')) {
                return true;
            }
        }

        if (dir == 'l') {
            if (defSign == '-' && (nextSign == 'F' || nextSign == 'L')) {
                return true;
            }

            if (defSign == '7' && (nextSign == 'F' || nextSign == 'L' || nextSign == '-')) {
                return true;
            }

            if (defSign == 'J' && (nextSign == 'F' || nextSign == 'L' || nextSign == '-')) {
                return true;
            }
        }

        if (dir == 'r') {
            if (defSign == '-' && (nextSign == '7' || nextSign == 'J')) {
                return true;
            }

            if (defSign == 'F' && (nextSign == '7' || nextSign == 'J' || nextSign == '-')) {
                return true;
            }

            if (defSign == 'L' && (nextSign == '7' || nextSign == 'J' || nextSign == '-')) {
                return true;
            }
        }
        return false;
    }

    public long partOne() {
        findStart();
        return 0;
    }

    public long partTwo() {
        return 0;
    }
}
