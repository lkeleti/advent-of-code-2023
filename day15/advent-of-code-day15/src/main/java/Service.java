import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private List<String> sequence = new ArrayList<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            List<List<Character>> rows = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                for (String s: line.split(",")) {
                    sequence.add(s.trim());
                }
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    public int partOne() {
        int total = 0;
        for (String s: sequence) {
            total += calcHash(s);
        }
        return total;
    }

    private int calcHash(String s) {
        int hash = 0;
        for (Character c: s.toCharArray()) {
            hash += c.charValue();
            hash *= 17;
            hash %= 256;
        }
        return hash;
    }

    public int partTwo() {
        return 0;
    }
}
