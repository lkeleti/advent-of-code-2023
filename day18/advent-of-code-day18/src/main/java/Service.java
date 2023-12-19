import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Instruct> instructions = new ArrayList<>();
    private List<List<String>> board = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(" ");
                instructions.add(new Instruct(datas[0].toCharArray()[0], Integer.parseInt(datas[1]),datas[2]));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private int[] findBoardSize() {
        int minX = 0;
        int minY = 0;
        int maxX = 0;
        int maxY = 0;
        int defX = 0;
        int defY = 0;
        for (Instruct i: instructions) {
            int direction = i.getDirection();
            int distance = i.getDistance();
            switch(direction) {
                case 'U':
                    defY -= distance;
                    break;
                case 'D':
                    defY += distance;
                    break;
                case 'L':
                    defX -= distance;
                    break;
                case 'R':
                    defX += distance;
                    break;
            }
            minX = Math.min(minX, defX);
            minY = Math.min(minY, defY);
            maxX = Math.max(maxX, defX);
            maxY = Math.max(maxY, defY);
        }
        return new int[] {minX, minY, maxX, maxY};
    }

    private void createBoard (int[] bounds) {
        int minX = bounds[0];
        int minY = bounds[1];
        int maxX = bounds[2];
        int maxY = bounds[3];
        int defX = Math.abs(minX);
        int defY = Math.abs(minY);

        for (int j = minY; j <= maxY; j++) {
            List<String> row = new ArrayList<>();
            for (int i = minX; i <= maxX; i++) {
                row.add(" ");
            }
            board.add(row);
        }

        for (Instruct i: instructions) {
            int direction = i.getDirection();
            int distance = i.getDistance();
            int newX = defX;
            int newY = defY;
            switch (direction) {
                case 'U':
                    for (int step = 0; step <= distance; step++ ) {
                        board.get(defY - step).set(defX, i.getColor());
                        board.get(defY - step).set(defX, "#");
                    }
                    defY -= distance;
                    break;
                case 'D':
                    for (int step = 0; step <= distance; step++ ) {
                        board.get(defY + step).set(defX, i.getColor());
                        board.get(defY + step).set(defX, "#");
                    }
                    defY+= distance;
                    break;
                case 'L':
                    for (int step = 0; step <= distance; step++ ) {
                        board.get(defY).set(defX - step, i.getColor());
                        board.get(defY).set(defX - step, "#");
                    }
                    defX -= distance;
                    break;
                case 'R':
                    for (int step = 0; step <= distance; step++ ) {
                        board.get(defY).set(defX + step, i.getColor());
                        board.get(defY).set(defX + step, "#");
                    }
                    defX += distance;
                    break;
            }
        }
    }
    public int partOne() {
        int[] result = findBoardSize();
        createBoard(result);
        drawBoard();
        return 0;
    }

    private void drawBoard() {
        for (List<String> row: board) {
            System.out.println(String.join("",row));
        }
    }

    public int partTwo() {
        return 0;
    }
}
