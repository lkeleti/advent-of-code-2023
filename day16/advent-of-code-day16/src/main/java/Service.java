import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Service {
    private final List<List<Character>> board = new ArrayList<>();
    private final Map<Cord,List<Cord>> visited = new HashMap<>();

    private int rowNumber;
    private int colNumber;

    private List<Cord[]> startPositions = new ArrayList<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (Character c: line.toCharArray()){
                    row.add(c);
                }
                board.add(row);
            }
            rowNumber = board.size();
            colNumber = board.getFirst().size();

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    public int partOne() {
        Cord defPos = new Cord(-1,0);
        Cord defDirection = new Cord(1,0);
        startPositions.add(new Cord[] {defPos, defDirection});

        while (!startPositions.isEmpty()) {
            Cord[] defCords = startPositions.getFirst();
            defPos = defCords[0];
            defDirection = defCords[1];
            while (!isVisited(defPos, defDirection) && !isOverTheBoard(defPos, defDirection)) {
                defPos = defPos.add(defDirection);
                updateVisited(defPos, defDirection);

                Character sign = board.get(defPos.getPosY()).get(defPos.getPosX());
                switch (sign) {
                    case '-':
                        defDirection = checkDash(defPos, defDirection);
                        break;
                    case '|':
                        defDirection = checkPipe(defPos, defDirection);
                        break;
                    case '\\':
                        defDirection = checkBackSlash(defPos, defDirection);
                        break;
                    case '/':
                        defDirection = checkSlash(defPos, defDirection);
                        break;
                }

            }
            startPositions.remove(defCords);
        }
        drawVisited();
        return visited.size();
        //6605 good answer
    }

    private void drawVisited() {
        Character[][] b = new Character[rowNumber][colNumber];
        for (int i = 0; i < colNumber; i++) {
            for (int j = 0; j < rowNumber; j++) {
                b[i][j] = '.';
            }
        }
        for (Cord v: visited.keySet()) {
            b[v.getPosY()][v.getPosX()] = '#';
        }

        for (Character[] row: b) {
            System.out.println(Arrays.stream(row).toList());
        }
    }

    private Cord checkSlash(Cord defPos, Cord defDirection) {
        if (defDirection.getPosX() == 1) {
            //right to up
            return new Cord(0,-1);
        }

        if (defDirection.getPosX() == -1) {
            //left to down
            return new Cord(0,1);
        }

        if (defDirection.getPosY() == 1) {
            //down to left
            return new Cord(-1,0);
        }

        if (defDirection.getPosY() == -1) {
            //up to right
            return new Cord(1,0);
        }
        return defDirection;
    }

    private Cord checkBackSlash(Cord defPos, Cord defDirection) {
        if (defDirection.getPosX() == 1) {
            //right to down
            return new Cord(0,1);
        }

        if (defDirection.getPosX() == -1) {
            //left to up
            return new Cord(0,-1);
        }

        if (defDirection.getPosY() == 1) {
            //down to right
            return new Cord(1,0);
        }

        if (defDirection.getPosY() == -1) {
            //up to left
            return new Cord(-1,0);
        }
        return defDirection;
    }

    private Cord checkPipe(Cord defPos, Cord defDirection) {
        if (defDirection.getPosX() == 0) {
            return defDirection;
        } else {
            Cord newDirectionOne = new Cord(0,1);
            Cord newDirectionTwo = new Cord(0,-1);
            startPositions.add(new Cord[] {defPos, newDirectionTwo});
            return newDirectionOne;
        }
    }

    private Cord checkDash(Cord defPos, Cord defDirection) {
        if (defDirection.getPosY() == 0) {
            return defDirection;
        } else {
            Cord newDirectionOne = new Cord(1,0);
            Cord newDirectionTwo = new Cord(-1,0);
            startPositions.add(new Cord[] {defPos, newDirectionTwo});
            return newDirectionOne;
        }
    }

    private void updateVisited(Cord defPos, Cord defDirection) {
        if (!visited.containsKey(defPos)) {
            visited.put(defPos, new ArrayList<>());
        }

        if (!isVisited(defPos, defDirection)) {
            visited.get(defPos).add(defDirection);
        }
    }

    private boolean isOverTheBoard(Cord defPos, Cord defDirection) {
        Cord newPos = defPos.add(defDirection);
        return (newPos.getPosX() < 0 || newPos.getPosX() > colNumber-1 || newPos.getPosY() < 0 || newPos.getPosY() > rowNumber-1);
    }

    private boolean isVisited(Cord defPos, Cord defDirection) {
        return visited.containsKey(defPos.add(defDirection)) && visited.get(defPos).contains(defDirection);
    }

    public int partTwo() {
        return 0;
    }

}
