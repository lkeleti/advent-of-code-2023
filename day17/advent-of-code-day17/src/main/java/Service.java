import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Service {

    private final List<List<Character>> board = new ArrayList<>();
    private int boardWith;
    private int boardHeight;

    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (Character c: line.toCharArray()) {
                    row.add(c);
                }
                board.add(row);
            }
            boardWith = board.getFirst().size();
            boardHeight = board.size();
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private int solvePartOne() {
        List<Dir> allDirection = new ArrayList<>();
        allDirection.add(Dir.UP);
        allDirection.add(Dir.DOWN);
        allDirection.add(Dir.LEFT);
        allDirection.add(Dir.RIGHT);
        Set<PathData> seen = new HashSet<>();
        PriorityQueue<PathData> pq = new PriorityQueue<>(Comparator.comparingInt(PathData::getHeatLost));
        pq.add(new PathData());

        while (!pq.isEmpty()) {
            PathData defElement = pq.poll();

            if (defElement.getPosition().getPosX() == boardWith - 1 && defElement.getPosition().getPosY() == boardHeight - 1) {
                return defElement.getHeatLost();
            }

            if (defElement.getPosition().getPosX() < 0 || defElement.getPosition().getPosX() >= boardWith ||
                    defElement.getPosition().getPosY() < 0 || defElement.getPosition().getPosY() >= boardHeight ||
                    seen.contains(defElement)) {
                continue;
            }

            seen.add(defElement);

            if (defElement.getSteps() < 3 && !defElement.getDirection().equals(Dir.NONE)) {
                Cord nextCord = defElement.getPosition().addDirection(defElement.getDirection());

                if (0 <= nextCord.getPosX() &&  nextCord.getPosX() < boardWith &&
                        0 <= nextCord.getPosY() &&  nextCord.getPosY() < boardHeight &&
                !seen.contains(new PathData(
                        defElement.getHeatLost() + Integer.parseInt(String.valueOf(board.get(nextCord.getPosY()).get(nextCord.getPosX()))),
                        nextCord,
                        defElement.getDirection(),
                        defElement.getSteps()+1))) {
                    pq.add(new PathData(
                            defElement.getHeatLost() + Integer.parseInt(String.valueOf(board.get(nextCord.getPosY()).get(nextCord.getPosX()))),
                            nextCord,
                            defElement.getDirection(),
                            defElement.getSteps()+1
                    ));
                }
            }

            for (Dir nextDirection : allDirection) {
                if (!nextDirection.equals(defElement.getDirection()) &&
                !nextDirection.equals(defElement.getReverseDirection())) {
                    Cord nextCord = defElement.getPosition().addDirection(nextDirection);

                    if (0 <= nextCord.getPosX() &&  nextCord.getPosX() < boardWith &&
                            0 <= nextCord.getPosY() &&  nextCord.getPosY() < boardHeight ) {
                        pq.add(new PathData(
                                defElement.getHeatLost() + Integer.parseInt(String.valueOf(board.get(nextCord.getPosY()).get(nextCord.getPosX()))),
                                nextCord,
                                nextDirection,
                                1
                        ));
                    }
                }
            }
        }
        return -1;
    }

    public int partOne() {
        return solvePartOne();
    }

    public int partTwo() {
        return 0;
    }
}
