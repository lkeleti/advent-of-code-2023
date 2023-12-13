import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Board> boards = new ArrayList<>();
    public void readInput(Path path) {
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
        return findMirrors();
    }

    private int findMirrors() {
        int total = 0;
        for (Board board: boards) {
            total += findMirror(board);
        }
        return total;
    }

    private int findMirror(Board board) {
        int verticalMirror = getVerticalMirror(board);
        int horizontalMirror = getHorizontalMirror(board);

        return Math.max(verticalMirror, horizontalMirror);
    }

    private int getVerticalMirror(Board board) {
        int verticalMirror = -1;
        for (int i = 0; i < board.getRowSize()-1; i++) {
           if (board.getRow(i).equals(board.getRow(i+1))) {
               //Is it really a mirror line?
               boolean symetric = true;
               int dist = Math.min(i, board.getRowSize()-(i+2));
               for (int j = 1; j <= dist; j++) {
                   if (!board.getRow(i-j).equals(board.getRow(i+j+1))) {
                       symetric = false;
                       break;
                   }
               }
               if (symetric) {
                   verticalMirror = (i+1) * 100;
                   break;
               }
           }
        }
        return verticalMirror;
    }

    private int getHorizontalMirror(Board board) {
        int horizontalMirror = -1;
        for (int i = 0; i < board.getColSize()-1; i++) {
            if (board.getColumn(i).equals(board.getColumn(i+1))) {
                boolean symetric = true;
                int dist = Math.min(i, board.getColSize()-(i+2));
                for (int j = 1; j <= dist; j++) {
                    if (!board.getColumn(i-j).equals(board.getColumn(i+j+1))) {
                        symetric = false;
                        break;
                    }
                }
                if (symetric) {
                    horizontalMirror = i+1;
                    break;
                }
            }
        }
        return horizontalMirror;
    }

    public int partTwo() {
        return 0;
    }
}
