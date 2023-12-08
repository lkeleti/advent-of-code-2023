import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Service {

    private char[] directions;
    private final Map<String, Node> nodes = new TreeMap<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    if (!line.contains("=")) {
                        directions = line.toCharArray();
                    } else {
                        String[] datas = line.split("=");
                        String name = datas[0].replace(" ","");
                        String[] dir = datas[1].replace(" ","").split(",");
                        String left = dir[0].replace("(","");
                        String right = dir[1].replace(")","");
                        nodes.put(name, new Node(name, left, right));
                    }
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    public int partOne() {
        int counter = 0;
        String defNode = "AAA";
        while (!defNode.equals("ZZZ")) {
            char dir = getDirection(counter);
            if (dir == 'L') {
                defNode = nodes.get(defNode).getLeft();
            } else {
                defNode = nodes.get(defNode).getRight();
            }
            counter++;
        }
        return counter;
    }

    private char getDirection(int counter) {
        int position = counter;
        if (counter > (directions.length-1)) {
            position = counter % directions.length;
        }

        return directions[position];
    }

    public int partTwo() {
        return 0;
    }
}
