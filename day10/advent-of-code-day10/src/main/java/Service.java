import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final List<List<Character>> board = new ArrayList<>();

    private Cord start;
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
                    start = new Cord(j,i);
                    System.out.println(start);
                    break;
                }
            }
        }
    }

    private boolean isValidSign(Direction dir, Character defSign, Character nextSign) {
        if (defSign == 'S') {
            return true;
        }
        if (dir == Direction.DOWN) {
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

        if (dir == Direction.UP) {
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

        if (dir == Direction.LEFT) {
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

        if (dir == Direction.RIGHT) {
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

    private void findPath() {
        List<Direction> directionList = new ArrayList<>();
        directionList.add(Direction.UP);
        directionList.add(Direction.DOWN);
        directionList.add(Direction.LEFT);
        directionList.add(Direction.RIGHT);
        List<Integer> lenghts = new ArrayList<>();

        for (Direction defDir: directionList) {
            int counter = 0;
            Cord defCord = start;
            Cord nextCord;
            Direction nextDir;
            while (defCord == start && counter > 0) {
                nextDir = getNextCord(defCord, defDir);
                nextCord = defCord.add(nextDir);
                if (isValidSign(defDir, board.get(defCord.getPosY()).get(defCord.getPosX()), board.get(nextCord.getPosY()).get(nextCord.getPosX()))) {
                    defCord = nextCord;
                } else {
                    counter = -1;
                    break;
                }
                counter++;
            }
            lenghts.add(counter);
        }
    }

    private Direction getNextCord(Cord defCord, Direction defDir) {
        Character defSign = board.get(defCord.getPosY()).get(defCord.getPosX());

        if (defSign =='S') {
            return defDir;
        }
        //ToDO
        return defDir;
    }

    public long partOne() {
        findStart();
        findPath();
        return 0;
    }

    public long partTwo() {
        return 0;
    }
}
