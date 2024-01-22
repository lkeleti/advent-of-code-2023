import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Service {

    private final List<Condition> conditions = new ArrayList<>();
    private final Map<MemoField,Long> memo = new Hashtable<>();
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
        for (Condition condition : conditions) {

            List<List<String>> symbol = new ArrayList<>();
            for (String s : condition.getSprings().split("")) {
                if (s.equals("?")) {
                    symbol.add(List.of("#", "."));
                } else {
                    symbol.add(List.of(s));
                }
            }
            long count = Itertools.getCartesianProduct(symbol).stream()
                    .filter(p -> isValidCondition(new Condition(String.join("", p), condition.getGroups())))
                    .count();
            result += count;
        }
        return result;
    }

    private long solvePartTwo() {
        long result = 0;
        for (Condition condition: conditions) {
            List<Integer> newGroup = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                for (int g: condition.getGroups()) {
                    newGroup.add(g);
                }
            }
            Condition newCondition = new Condition(
                    ((condition.getSprings() + "?").repeat(5)).substring(0,(condition.getSprings().length()*5)+4),
                    newGroup
            );
            memo.clear();
            long count = countPermutations("." + newCondition.getSprings() + ".",newCondition.getGroups());
            result += count;
        }
        return result;
    }

    private long countPermutations(String springs, List<Integer> groups) {

        if (groups.isEmpty()){
            return springs.contains("#") ? 0: 1;
        }

        int size = groups.getFirst();
        groups = groups.subList(1, groups.size());

        MemoField memoField = new MemoField(springs, groups);
        if (memo.containsKey(memoField)) {
            return memo.get(memoField);
        }

        long count = 0;
        for (int end = 0; end < springs.length(); end++) {
            int start = end - (size - 1);
            if (fits(springs, start, end)) {
                count += countPermutations(springs.substring(end + 1), groups);
            }
        }
        memo.put(new MemoField(springs,groups), count);
        return count;
    }

    private static void getKey(String springs, List<Integer> groups, List<Integer> memoInts) {
        memoInts.clear();
        for (int i: springs.toCharArray()) {
            memoInts.add(i);
        }

        for (int i: groups) {
            memoInts.add(i);
        }
    }

    private boolean fits(String springs, int start, int end) {
        if (start -1 < 0 || end + 1 >= springs.length()) {
            return false;
        }

        if (springs.charAt(start-1) == '#' || springs.charAt(end + 1) == '#') {
            return false;
        }

        if (springs.substring(0, start).contains("#")) {
            return false;
        }

        for (int i = start; i < end+1; i++) {
            if (springs.charAt(i) == '.') {
                return false;
            }
        }
        return true;
    }

    /*private long countPermutations(String springs, List<Integer> groups, int groupLoc) {
        if (springs.isEmpty()) {
            return (groups.isEmpty() && groupLoc == 0) ? 1 : 0;
        }
        long results = 0;

        List<String> possibilities = new ArrayList<>();
        if (springs.charAt(0) == '?') {
            possibilities.add(".");
            possibilities.add("#");
        } else {
            possibilities.add(springs.substring(0,1));
        }

        for (String p: possibilities) {
            if (p.equals("#")) {
                results += countPermutations(springs.substring(1), groups, groupLoc + 1);
            } else {
                if (groupLoc > 0) {
                    if (!groups.isEmpty() && groups.getFirst() == groupLoc) {
                        results += countPermutations(springs.substring(1), groups.subList(1, groups.size()), 0);
                    }
                } else {
                    results = results + countPermutations(springs.substring(1), groups, 0);
                }

            }
        }
        return results;
    }*/

    public long partOne() {
        return solvePartOne();
    }

    public long partTwo() {
        return solvePartTwo();
    }
}