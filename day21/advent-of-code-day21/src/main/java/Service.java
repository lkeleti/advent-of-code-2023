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
        boardWith = board.getFirst().size();
        boardHeight = board.size();
        for (Integer i = 0; i< boardWith; i++) {
            for (Integer j = 0; j< boardHeight; j++) {
                if (board.get(i).get(j) == 'S') {
                    return new Cord(i.longValue(), j.longValue());
                }
            }
        }
        throw new IllegalArgumentException("Cannot find start point!");
    }

    public int partOne() {
        Cord start = findStart();
        return fill(start, 64);
    }

    private int fill(Cord start, int cikl) {
        Set<Cord> nextCords = new HashSet<>();
        nextCords.add(start);
        for (int i = 0; i < cikl; i++) {
            List<Cord> defCords = List.copyOf(nextCords);
            nextCords.clear();
            for (Cord c: defCords) {

                Cord up = c.addDirection(Direction.UP);
                if (up.getPosY() >=0 && !board.get(up.getPosY()).get(up.getPosX()).equals('#')) {
                    nextCords.add(up);
                }

                Cord down = c.addDirection(Direction.DOWN);
                if (down.getPosY() < boardHeight && !board.get(down.getPosY()).get(down.getPosX()).equals('#')) {
                    nextCords.add(down);
                }

                Cord left = c.addDirection(Direction.LEFT);
                if (left.getPosX() >= 0 && !board.get(left.getPosY()).get(left.getPosX()).equals('#')) {
                    nextCords.add(left);
                }

                Cord right = c.addDirection(Direction.RIGHT);
                if (right.getPosX() < boardWith && !board.get(right.getPosY()).get(right.getPosX()).equals('#')) {
                    nextCords.add(right);
                }
            }
        }
        return nextCords.size();
    }

    public long partTwo() {
        Cord start = findStart();
        long steps = 26501365;
        int size = boardWith;
        long gridWidth = steps / size - 1;
        long odd = (long)Math.pow((gridWidth / 2 * 2 + 1), 2);
        long even = (long)Math.pow(((gridWidth + 1) / 2 * 2), 2);

        int oddPoints = fill(start, size * 2 + 1);
        int evenPoints = fill(start, size * 2);

        int cornerT = fill(new Cord(Integer.toUnsignedLong(size - 1), start.getPosYLong()), size - 1);
        int cornerR = fill(new Cord(start.getPosXLong(), 0L), size - 1);
        int cornerB = fill(new Cord(0L, start.getPosYLong()), size - 1);
        int cornerL = fill(new Cord(start.getPosXLong(), Integer.toUnsignedLong(size - 1)), size - 1);

        int smallTR = fill(new Cord(Integer.toUnsignedLong(size - 1), 0L), size / 2 - 1);
        int smallTL = fill(new Cord(Integer.toUnsignedLong(size - 1), Integer.toUnsignedLong(size - 1)), size / 2 - 1);
        int smallBR = fill(new Cord(0L, 0L), size / 2 - 1);
        int smallBL = fill(new Cord(0L, Integer.toUnsignedLong(size - 1)), size / 2 - 1);

        int largeTR = fill(new Cord(Integer.toUnsignedLong(size - 1), 0L), size * 3 / 2 - 1);
        int largeTL = fill(new Cord(Integer.toUnsignedLong(size - 1), Integer.toUnsignedLong(size - 1)), size * 3 / 2 - 1);
        int largeBR = fill(new Cord(0L, 0L), size * 3 / 2 - 1);
        int largeBL = fill(new Cord(0L, Integer.toUnsignedLong(size - 1)), size * 3 / 2 - 1);

        return odd * oddPoints +
                even * evenPoints +
                cornerT + cornerR + cornerB + cornerL +
                (gridWidth + 1) * (smallTR + smallTL + smallBR + smallBL) +
                gridWidth * (largeTR + largeTL + largeBR + largeBL);
    }

    private int fillWithInterpolate(Cord start, int cikl) {
        Set<Cord> nextCords = new HashSet<>();
        nextCords.add(start);
        for (int i = 0; i < cikl; i++) {
            List<Cord> defCords = List.copyOf(nextCords);
            nextCords.clear();
            for (Cord c: defCords) {

                Cord up = c.addDirection(Direction.UP);
                Cord upInterpolate = up.interpolate(boardWith, boardHeight);
                if (!board.get(upInterpolate.getPosY()).get(upInterpolate.getPosX()).equals('#')) {
                    nextCords.add(up);
                }

                Cord down = c.addDirection(Direction.DOWN);
                Cord downInterpolate = down.interpolate(boardWith, boardHeight);
                if (!board.get(downInterpolate.getPosY()).get(downInterpolate.getPosX()).equals('#')) {
                    nextCords.add(down);
                }

                Cord left = c.addDirection(Direction.LEFT);
                Cord leftInterpolate = left.interpolate(boardWith, boardHeight);
                if (!board.get(leftInterpolate.getPosY()).get(leftInterpolate.getPosX()).equals('#')) {
                    nextCords.add(left);
                }

                Cord right = c.addDirection(Direction.RIGHT);
                Cord rightInterpolate = right.interpolate(boardWith, boardHeight);
                if (!board.get(rightInterpolate.getPosY()).get(rightInterpolate.getPosX()).equals('#')) {
                    nextCords.add(right);
                }
            }
        }
        return nextCords.size();
    }
}
