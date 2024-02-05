import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Service {

    private List<Cube> cubes = new ArrayList<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] startEnd = line.split("~");
                String[] start = startEnd[0].split(",");
                String[] end = startEnd[1].split(",");

                cubes.add(new Cube(
                        new Cord(
                        Integer.parseInt(start[0]),
                        Integer.parseInt(start[1]),
                        Integer.parseInt(start[2])
                ), new Cord(
                        Integer.parseInt(end[0]),
                        Integer.parseInt(end[1]),
                        Integer.parseInt(end[2])
                )));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private int freeFall() {
        cubes.sort(Comparator.comparingInt(Cube::getBottom));
        //cubes = cubes.stream().sorted().toList();
        int counter = 0;
        for (int i = 0; i < cubes.size(); i++) {
            if (cubes.get(i).getBottom() > 1) {
                boolean cycle = true;
                while (cycle) {
                    cubes.get(i).moveDown();
                    counter++;
                    if (collide()) {
                        cubes.get(i).moveUp();
                        counter--;
                        cycle = false;
                    } else if (cubes.get(i).getBottom() == 1) {
                        cycle = false;
                    }
                }
            }
        }
        return counter;
    }
    public int partOne() {
        freeFall();
        int counter = 0;
        return counter;
    }

    private boolean collide() {
        for(int i=0; i < cubes.size()-1; i++) {
            for(int j = i + 1; j < cubes.size(); j++) {
                if (cubes.get(i).isCollide(cubes.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public int partTwo() {
        return 0;
    }
}
