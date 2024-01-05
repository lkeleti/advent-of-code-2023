import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final List<List<Character>> board = new ArrayList<>();
    private Cord start;
    private Cord end;
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            boolean opLines = true;
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (Character c: line.toCharArray()) {
                    row.add(c);
                }
                board.add(row);
            }
            findStartEnd();
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private void findStartEnd() {
        for (int x = 0; x < board.getFirst().size(); x++) {
            if (board.getFirst().get(x) == '.') {
                start = new Cord(x, 0);
            }

            if (board.getLast().get(x) == '.') {
                end = new Cord(x, board.size()-1);
            }
        }
    }

    private List<Cord> findNextMoves(Cord defPos) {
        Character defSign = board.get(defPos.getPosY()).get(defPos.getPosX());
        List<Cord> nextCords = new ArrayList<>();

        Cord up = defPos.addCord(Direction.UP.getCord());
        Cord down = defPos.addCord(Direction.DOWN.getCord());
        Cord left = defPos.addCord(Direction.LEFT.getCord());
        Cord right = defPos.addCord(Direction.RIGHT.getCord());

        switch (defSign) {
            case '>':
                nextCords.add(right);
                break;
            case '<':
                nextCords.add(left);
                break;
            case 'v':
                nextCords.add(down);
                break;
            case '^':
                nextCords.add(up);
                break;
            default:
                checkSpaceOnBoard(up, down, left, right, nextCords);
                break;
        }
        return nextCords;
    }

    private void checkSpaceOnBoard(Cord up, Cord down, Cord left, Cord right, List<Cord> nextCords) {
        List<Cord> directions = new ArrayList<>();
        directions.add(up);
        directions.add(down);
        directions.add(left);
        directions.add(right);

        for (Cord d: directions) {
            if (d.getPosX() >= 0 && d.getPosX() < board.getFirst().size() &&
                    d.getPosY() >= 0 && d.getPosY() < board.size() &&
                    board.get(d.getPosY()).get(d.getPosX()) != '#') {
                nextCords.add(d);
            }
        }
    }

    public long partOne() {
        return 0;
    }
    public int partTwo() {
        return 0;
    }
}
