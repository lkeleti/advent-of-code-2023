import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Service {
    private final List<List<Character>> board = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (Character c : line.toCharArray()) {
                    row.add(c);
                }
                board.add(row);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private Cord findStart() {
        for (int i = 0; i< board.getFirst().size(); i++) {
            for (int j = 0; j< board.size(); j++) {
                if (board.get(i).get(j) == 'S') {
                    return new Cord(i,j);
                }
            }
        }
        throw new IllegalArgumentException("Cannot find start point!");
    }

    public int partOne() {
        Cord start = findStart();
        Set<Cord> nextCords = new HashSet<>();
        nextCords.add(start);
        for (int i = 0; i < 64; i++) {
            List<Cord> defCords = List.copyOf(nextCords);
            nextCords.clear();
            for (Cord c: defCords) {

                Cord up = c.addDirection(Direction.UP);
                if (up.getPosY() >=0 && !board.get(up.getPosY()).get(up.getPosX()).equals('#')) {
                    nextCords.add(up);
                }

                Cord down = c.addDirection(Direction.DOWN);
                if (down.getPosY() <= board.size() && !board.get(down.getPosY()).get(down.getPosX()).equals('#')) {
                    nextCords.add(down);
                }

                Cord left = c.addDirection(Direction.LEFT);
                if (left.getPosX() >= 0 && !board.get(left.getPosY()).get(left.getPosX()).equals('#')) {
                    nextCords.add(left);
                }

                Cord right = c.addDirection(Direction.RIGHT);
                if (right.getPosX() <= board.getFirst().size() && !board.get(right.getPosY()).get(right.getPosX()).equals('#')) {
                    nextCords.add(right);
                }
            }
        }
        return nextCords.size();
    }

    public int partTwo() {
        return 0;
    }

}
