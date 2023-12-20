import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Instruct> instructions = new ArrayList<>();
    private List<List<String>> board = new ArrayList<>();
    private List<Cord> polygon = new ArrayList<>();
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

    private long calcArea() {
        int i;
        int j;

        double area = 0.0;
        for (i = 0; i < polygon.size()-1; i++) {
            j = i+1;
            area += ((polygon.get(i).getPosX()* polygon.get(j).getPosY()) - (polygon.get(j).getPosX()*polygon.get(i).getPosY()));
        }
        area /= 2.0;
        return (long)Math.abs(area);
    }

    private long findPolygon() {
        int startX = 0;
        for (int i = 0; i < board.getFirst().size(); i++) {
            if (!board.getFirst().get(i).equals(" ")) {
                startX = i;
                break;
            }
        }
        polygon.add(new Cord(startX, 0));

        int defX = 0;
        int defY = 0;

        Cord result = findNextPoint(startX,0);
        defX = result.getPosX();
        defY = result.getPosY();
        polygon.add(new Cord(defX, defY));

        while (!(defX == startX && defY == 0)) {
            result = findNextPoint(defX, defY);
            defX = result.getPosX();
            defY = result.getPosY();
            polygon.add(new Cord(defX, defY));
        }
        return calcArea();
    }

    private Cord findNextPoint(int defX, int defY) {
        if (defX < board.getFirst().size()-1 && !board.get(defY).get(defX+1).equals(" ") && !polygon.contains(new Cord(defX+1, defY))) {
            return new Cord(defX+1, defY);
        }

        if (defX > 0 && !board.get(defY).get(defX-1).equals(" ") && !polygon.contains(new Cord(defX-1, defY))) {
            return new Cord(defX-1, defY);
        }

        if (defY < board.size()-1 && !board.get(defY+1).get(defX).equals(" ") && !polygon.contains(new Cord(defX, defY+1))) {
            return new Cord(defX, defY+1);
        }

        if (defY > 0 && !board.get(defY-1).get(defX).equals(" ") && !polygon.contains(new Cord(defX, defY-1))) {
            return new Cord(defX, defY-1);
        }
        return polygon.get(0);
    }

    private void createPolygon() {
        int defX = 0;
        int defY = 0;
        polygon.add(new Cord(defX,defY));
        for (Instruct i: instructions) {
            int direction = i.getDirection();
            int distance = i.getDistance();

            switch (direction) {
                case 'U':
                    defY -= distance;
                    break;
                case 'D':
                    defY+= distance;
                    break;
                case 'L':
                    defX -= distance;
                    break;
                case 'R':
                    defX += distance;
                    break;
            }
            polygon.add(new Cord(defX,defY));
        }
    }
    public long partOne() {
        //int[] result = findBoardSize();
        //createBoard(result);
        //long area =findPolygon();
        //drawBoard();
        createPolygon();
        long area = calcArea();


        //32953 too low
        return area;
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
