import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final List<Condition> conditions = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(" ");
                String[] numbers = datas[1].split(",");
                List<Integer> groups= new ArrayList<>();
                for (String number: numbers) {
                    groups.add(Integer.parseInt(number));
                }
                conditions.add( new Condition(datas[0], groups));

            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    public int partOne() {
        return 0;
    }

    public int partTwo() {
        return 0;
    }
}
