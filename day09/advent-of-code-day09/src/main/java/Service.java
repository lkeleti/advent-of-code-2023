import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final List<List<Long>> history = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Long> numbers = new ArrayList<>();
                String[] datas = line.split(" ");
                for (String n: datas) {
                    numbers.add(Long.parseLong(n));
                }
                history.add(numbers);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    private int findNextValueRight(List<Long> numbers) {
        List<List<Long>> table = new ArrayList<>();
        table.add(numbers);
        int defRow = 0;
        while (table.get(table.size()-1).stream().filter(n->n==0).count() != table.get(table.size()-1).size()) {
            List<Long> nextRow = new ArrayList<>();
            for (int i = 0; i < table.get(defRow).size()-1;i++) {
                long difference = table.get(defRow).get(i+1) - table.get(defRow).get(i);
                nextRow.add(difference);
            }
            table.add(nextRow);
            defRow++;
        }
        int lastValue = 0;
        for (int i = table.size()-1; i >= 0; i--) {
            lastValue += table.get(i).getLast();
        }
        return lastValue;
    }
    public int partOne() {
        int sum = 0;
        for (List<Long> h: history) {
            sum += findNextValueRight(h);
        }
        return sum;

    }

    private long findNextValueLeft(List<Long> numbers) {
        List<List<Long>> table = new ArrayList<>();
        table.add(numbers);
        int defRow = 0;
        while (table.get(table.size()-1).stream().filter(n->n==0).count() != table.get(table.size()-1).size()) {
            List<Long> nextRow = new ArrayList<>();
            for (int i = 0; i < table.get(defRow).size()-1;i++) {
                long difference = table.get(defRow).get(i+1) - table.get(defRow).get(i);
                nextRow.add(difference);
            }
            table.add(nextRow);
            defRow++;
        }
        long lastValue = 0;
        for (int i = table.size()-1; i >= 0; i--) {
            lastValue = table.get(i).getFirst() - lastValue;
        }
        return lastValue;
    }
    public long partTwo() {
        long sum = 0;
        for (List<Long> h: history) {
            sum += findNextValueLeft(h);
        }
        return sum;
    }
}
