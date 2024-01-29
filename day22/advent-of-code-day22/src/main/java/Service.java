import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
    public int partOne() {
        cubes = cubes.stream().sorted().toList();
        return 0;
    }

    public int partTwo() {
        return 0;
    }
}
