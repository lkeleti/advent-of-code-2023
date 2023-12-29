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
                    break;
                }
            }
        }
    }

    private int findPath() {
        List<Direction> directionList = new ArrayList<>();
        directionList.add(Direction.UP);
        directionList.add(Direction.DOWN);
        directionList.add(Direction.LEFT);
        directionList.add(Direction.RIGHT);
        List<Integer> lengths = new ArrayList<>();

        for (Direction defDir: directionList) {
            int counter = 0;
            Cord defCord = start;
            Cord nextCord;
            Direction nextDir;
            while (!(defCord.equals(start) && counter > 0)) {
                try {
                    nextDir = getNextCord(defCord, defDir);
                } catch (IllegalArgumentException iae) {
                    counter = -1;
                    break;
                }
                nextCord = defCord.add(nextDir);
                defCord = nextCord;
                defDir = nextDir;
                if (nextCord.getPosX() < 0 || nextCord.getPosY() < 0 ) {
                    counter = -1;
                    break;
                }
                counter++;
            }
            lengths.add(counter);
        }
        return lengths.stream()
                .mapToInt(l-> l)
                .max().getAsInt()/2;
    }

    private Direction getNextCord(Cord defCord, Direction defDir) {
        Character defSign = board.get(defCord.getPosY()).get(defCord.getPosX());

        if (defSign =='S') {
            return defDir;
        }

        if (defSign =='|') {
            if (defDir == Direction.UP) {
                return Direction.UP;
            }

            if (defDir == Direction.DOWN) {
                return Direction.DOWN;
            }
        }

        if (defSign =='-') {
            if (defDir == Direction.LEFT) {
                return Direction.LEFT;
            }

            if (defDir == Direction.RIGHT) {
                return Direction.RIGHT;
            }
        }

        if (defSign =='L') {
            if (defDir == Direction.LEFT) {
                return Direction.UP;
            }

            if (defDir == Direction.DOWN) {
                return Direction.RIGHT;
            }
        }

        if (defSign =='J') {
            if (defDir == Direction.DOWN) {
                return Direction.LEFT;
            }

            if (defDir == Direction.RIGHT) {
                return Direction.UP;
            }
        }

        if (defSign =='7') {
            if (defDir == Direction.RIGHT) {
                return Direction.DOWN;
            }

            if (defDir == Direction.UP) {
                return Direction.LEFT;
            }
        }

        if (defSign =='F') {
            if (defDir == Direction.LEFT) {
                return Direction.DOWN;
            }

            if (defDir == Direction.UP) {
                return Direction.RIGHT;
            }
        }

        throw new IllegalArgumentException("Impossible combination: " + defSign + " " + defDir + " " + defCord);
    }

    public long partOne() {
        findStart();
        return findPath();
    }

    public long partTwo() {
        return 0;
    }
}
