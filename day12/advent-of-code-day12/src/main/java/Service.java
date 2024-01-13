import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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

    private boolean isValidCondition(Condition condition) {
        String[] springs = condition.getSprings().split("\\.");
        List<String> springList = Arrays.stream(springs).filter(s->!s.isEmpty()).toList();
        if (springList.size() != condition.getGroups().size()) {
            return false;
        }
        for (int i = 0; i < springList.size(); i++) {
            if (springList.get(i).length() != condition.getGroups().get(i)) {
                return false;
            }
        }
        return true;
    }

    private long solvePartOne() {
        long result = 0;
        for (Condition condition: conditions) {
            List<List<String>> symbol = new ArrayList<>();
            for (String s: condition.getSprings().split("")) {
                if (s.equals("?")) {
                    symbol.add(List.of("#", "."));
                } else {
                    symbol.add(List.of(s));
                }
            }

            result += Itertools.getCartesianProduct(symbol).stream()
                    .filter(p->isValidCondition(new Condition(String.join("",p),condition.getGroups())))
                    .count();
        }
        return result;
    }

    public long partOne() {
        return solvePartOne();
    }

    public int partTwo() {
        return 0;
    }
}
