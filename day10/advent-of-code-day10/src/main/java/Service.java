import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Service {
    private final static Map<Character, Direction> signs = new TreeMap<>();
    private List<List<Direction>> surfaces = new ArrayList<>();
    private Direction start;
    static {
        signs.put('|', new Direction(0,1)); //n-s
        signs.put('-', new Direction(-1,0)); //e-w
        signs.put('L', new Direction(1,-1)); //n-e
        signs.put('J', new Direction(0,-1)); //n-w
        signs.put('7', new Direction(-1,1)); //s-w
        signs.put('F', new Direction(1,1)); //s-e
        signs.put('.', new Direction(0,0)); //ground
        signs.put('S', new Direction(9,9)); //Start
    }
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Direction> row = new ArrayList<>();
                for (char c: line.toCharArray()) {
                    row.add(signs.get(c));
                }
                surfaces.add(row);
            }
            findStart();
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    private void findStart() {
        for (int i=0; i < surfaces.size(); i++) {
            for (int j = 0; j < surfaces.get(0).size(); j++) {
                if (surfaces.get(i).get(j).equals(new Direction(9,9))) {
                    start = new Direction(j,i);
                    System.out.println(start);
                    break;
                }
            }
        }
    }

    public long partOne() {
        return 0;
    }

    public long partTwo() {
        return 0;
    }
}
