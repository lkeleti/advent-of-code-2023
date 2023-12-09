import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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

    public long partOne() {
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

    private char getDirection(long counter) {
        long position = counter;
        if (counter > (directions.length-1)) {
            position = counter % directions.length;
        }

        return directions[(int)position];
    }

    public long partTwo() {
        long counter = 0;
        List<String> defNodes = new ArrayList<>();
        for (String node: nodes.keySet()) {
            if (node.endsWith("A")) {
                defNodes.add(node);
            }
        }

        boolean done = false;

        while (!done) {
            char dir = getDirection(counter);
            for (int i = 0; i< defNodes.size();i++) {
                String defNode = defNodes.get(i);
                if (dir == 'L') {
                    defNode = nodes.get(defNode).getLeft();
                } else {
                    defNode = nodes.get(defNode).getRight();
                }
                defNodes.set(i,defNode);
            }
                counter++;
            long lastNodeCount = defNodes.stream().filter(n->n.endsWith("Z")).count();
            if (lastNodeCount > 3 ) {
                System.out.println(counter);
            }
            if( lastNodeCount == defNodes.size()) {
                done = true;
            }
        }
        //103194625
        return counter;
    }
}
