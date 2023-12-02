
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Service {
    private static final Map<Integer, List<List<Color>>> games = new TreeMap<>();
    private static final Map<String, Integer> cubes = new TreeMap<>();
    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final String BLUE = "blue";
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                readFromLine(line);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    private static void readFromLine(String line) {
        String[] datas = line.split(":");
        String[] gameName = datas[0].split(" ");
        int id = Integer.parseInt(gameName[1]);
        List<List<Color>> gameParts = new ArrayList<>();
        String[] pulls = datas[1].split(";");
        for (String pull: pulls) {
            String[] pullValue = pull.split(",");
            List<Color> colorList = new ArrayList<>();
            for (String color: pullValue) {
                String[] colorDatas = color.split(" ");
                colorList.add( new Color(
                        colorDatas[2], Integer.parseInt(colorDatas[1])
                ));
            }
            gameParts.add(colorList);
        }
        games.put(id,gameParts);
    }

    public int partOne() {
        initCubes();
        int sum = 0;
        for (Map.Entry<Integer, List<List<Color>>> entry: games.entrySet()) {
            int id = entry.getKey();
            boolean good = true;
            List<List<Color>> parts = entry.getValue();
            for (List<Color> part: parts) {
                good = checkPart(part, good);
            }
            if (good) {
                sum += id;
            }
        }
        return sum;
    }

    private boolean checkPart(List<Color> part, boolean good) {
        for (Color color: part) {
            for (Map.Entry<String, Integer> cube: cubes.entrySet()) {
                if (color.getName().equals(cube.getKey()) && color.getValue() > cube.getValue()) {
                    good = false;
                    break;
                }
            }
        }
        return good;
    }

    private void initCubes() {
        cubes.put(RED,12);
        cubes.put(GREEN,13);
        cubes.put(BLUE,14);
    }

    private void clearCubes() {
        cubes.put(RED,0);
        cubes.put(GREEN,0);
        cubes.put(BLUE,0);
    }
    private int getSumOfCubes() {
        return cubes.get(RED) *
                cubes.get(GREEN)*
                cubes.get(BLUE);
    }

    public int partTwo() {
        int sum = 0;
        for (Map.Entry<Integer, List<List<Color>>> entry: games.entrySet()) {
            clearCubes();
            List<List<Color>> parts = entry.getValue();
            for (List<Color> part: parts) {
                for (Color color: part) {
                    countMinCubes(color);
                }
            }
            sum += getSumOfCubes();
        }
        return sum;
    }

    private static void countMinCubes(Color color) {
        for (Map.Entry<String, Integer> cube: cubes.entrySet()) {
            if (color.getName().equals(cube.getKey()) && color.getValue() > cube.getValue()) {
                cube.setValue(color.getValue());
            }
        }
    }
}
