import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Service {

    private static final List<String> lines = new ArrayList<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    public int partOne() {
        int sum = 0;

        for (String line: lines) {
            char[] charArray = line.toCharArray();
            sum += findNumber(charArray);
        }
        return sum;
    }

    private int findNumber(char[] charArray) {
        boolean start = true;
        int first = 0;
        int last = 0;
        for (char c: charArray) {
            if (c >= '0' && c <= '9') {
                if (start) {
                    first = c - 48;
                    last = c - 48;
                    start = false;
                } else {
                    last = c - 48;
                }
            }
        }
        return (first * 10 + last);
    }

    public int partTwo() {
        int sum = 0;
        Map<String, Integer> helper = intiHelper();

        for (String line: lines) {
            sum += findMultiNumber(line, helper);
        }
        return sum;
    }

    private int findMultiNumber(String line, Map<String, Integer> helper) {
        int firstPos = Integer.MAX_VALUE;
        int first = 0;
        int lastPos = Integer.MIN_VALUE;
        int last = 0;

        for (Map.Entry<String,Integer> entry : helper.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();

            int defPos = line.indexOf(key);

            if (defPos != -1 && defPos < firstPos) {
                first = value;
                firstPos = defPos;
            }

            defPos = line.lastIndexOf(key);
            if (defPos > lastPos) {
                last = value;
                lastPos = defPos;
            }
        }
        return first * 10 + last;
    }

    private Map<String, Integer> intiHelper() {
        Map<String, Integer> helper = new TreeMap<>();
        helper.put("0", 0);
        helper.put("1", 1);
        helper.put("2", 2);
        helper.put("3", 3);
        helper.put("4", 4);
        helper.put("5", 5);
        helper.put("6", 6);
        helper.put("7", 7);
        helper.put("8", 8);
        helper.put("9", 9);
        helper.put("one", 1);
        helper.put("two", 2);
        helper.put("three", 3);
        helper.put("four", 4);
        helper.put("five", 5);
        helper.put("six", 6);
        helper.put("seven", 7);
        helper.put("eight", 8);
        helper.put("nine", 9);
        return helper;
    }
}
