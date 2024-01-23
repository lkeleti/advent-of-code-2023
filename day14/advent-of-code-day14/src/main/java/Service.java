import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Service {

    private final List<List<Character>> board = new ArrayList<>();
    private int cycleLength;

    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            List<List<Character>> rows = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (Character c:line.toCharArray()) {
                    row.add(c);
                }
                board.add(row);
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }
    public int partOne() {
        tiltNorth();
        return calcWeight();
    }

    private void tiltNorth() {
        for (int i = 1; i < board.size(); i++) {
            for (int j = 0; j < board.getFirst().size(); j++) {
                if (board.get(i).get(j) == 'O') {
                    for (int k = i - 1; k >=0; k--) {
                        if (board.get(k).get(j) == '.') {
                            board.get(k).set(j,'O');
                            board.get(k+1).set(j,'.');
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private void tiltWest() {
        for (int j = 1; j < board.getFirst().size(); j++) {
            for (int i = 0; i < board.size(); i++) {
                if (board.get(i).get(j) == 'O') {
                    for (int k = j - 1; k >=0; k--) {
                        if (board.get(i).get(k) == '.') {
                            board.get(i).set(k,'O');
                            board.get(i).set(k+1,'.');
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private void tiltEast() {
        for (int j = board.getFirst().size() -2; j >= 0; j--) {
            for (int i = 0; i < board.size(); i++) {
                if (board.get(i).get(j) == 'O') {
                    for (int k = j + 1; k < board.getFirst().size(); k++) {
                        if (board.get(i).get(k) == '.') {
                            board.get(i).set(k,'O');
                            board.get(i).set(k-1,'.');
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private void tiltSouth() {
        for (int i = board.size()-2; i >= 0; i--) {
            for (int j = 0; j < board.getFirst().size(); j++) {
                if (board.get(i).get(j) == 'O') {
                    for (int k = i + 1; k < board.size(); k++) {
                        if (board.get(k).get(j) == '.') {
                            board.get(k).set(j,'O');
                            board.get(k-1).set(j,'.');
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private int calcWeight() {
        int total = 0;
        for (int i = board.size()-1; i >= 0; i--) {
            int counter = 0;
            for (int j = 0; j < board.getFirst().size(); j++) {
                if (board.get(i).get(j) == 'O') {
                    counter++;
                }
            }
            total += ((board.size()-i) * counter);
        }
        return total;
    }

    private void tilt() {
        tiltNorth();
        tiltWest();
        tiltSouth();
        tiltEast();
    }

    private int calcHash() {
        StringBuilder line = new StringBuilder();
        for (int i = board.size()-1; i >= 0; i--) {
            for (int j = 0; j < board.getFirst().size(); j++) {
                line.append(board.get(i).get(j));
            }
        }
        return line.toString().hashCode();
    }

    private int findCycle() {
        Map<Integer, Integer> boardHash = new HashMap<>();
        int counter = 0;
        while (true) {
            tilt();
            int hash = calcHash();
            if (boardHash.containsKey(hash)) {
                cycleLength = counter - boardHash.get(hash);
                return counter;
            } else {
                boardHash.put(hash,counter);
                counter++;
            }
        }
    }

    public int partTwo() {
        int stepsToCycle = findCycle();
        int spinsRemaining = 1_000_000_000 - stepsToCycle;
        int fullCycles = spinsRemaining / cycleLength;
        spinsRemaining -= (fullCycles * cycleLength);

        while (spinsRemaining > 1) {
            tilt();
            spinsRemaining--;
        }
        //drawBoard();
        return calcWeight();
    }

    private void drawBoard() {
        for (List<Character> row: board) {
            System.out.println(row);
        }
        System.out.println("");
    }
}
