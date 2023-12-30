import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final List<List<Character>> board = new ArrayList<>();
    private final List<Cord> possiblePath = new ArrayList<>();

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

        for (Direction defDir: directionList) {
            int counter = 0;
            Cord defCord = start;
            Cord nextCord;
            Direction nextDir = null;
            boolean goodTry = true;
            while (!(defCord.equals(start) && counter > 0)) {
                try {
                    nextDir = getNextCord(defCord, defDir);
                } catch (IllegalArgumentException iae) {
                    goodTry = false;
                    nextDir  = Direction.UP;
                }

                nextCord = defCord.add(nextDir);
                if (goodTry && !(nextCord.getPosX() < 0 || nextCord.getPosY() < 0 )) {
                    defCord = nextCord;
                    defDir = nextDir;
                } else {
                    counter = -1;
                    possiblePath.clear();
                    break;
                }

                possiblePath.add(defCord);
                counter++;
            }
            if (counter > 0) {
                return counter/2;
            }
        }
        throw new IllegalArgumentException("Cannot find right way!");
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
        int counter = 0;
        for (int y=0; y < board.size(); y++) {
            boolean insideTheLoop = false;
            for (int x = 0; x < board.getFirst().size(); x++) {
                Character defSign = board.get(y).get(x);
                Cord defCord = new Cord(x,y);

                if (possiblePath.contains(defCord) && (defSign.equals('|') || defSign.equals('L') || defSign.equals('J') || defSign.equals('S'))) {
                    insideTheLoop = !insideTheLoop;
                }

                if (!possiblePath.contains(defCord) && insideTheLoop) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
