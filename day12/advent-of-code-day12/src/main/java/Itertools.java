import java.util.*;
import java.util.stream.Stream;

public class Itertools {

    private Itertools() {
    }

    public static List<List<String>> getCartesianProduct(List<List<String>> sets) {
        return cartesianProduct(sets,0).toList();
    }

    private static Stream<List<String>> cartesianProduct(List<List<String>> sets, int index) {
        if (index == sets.size()) {
            List<String> emptyList = new ArrayList<>();
            return Stream.of(emptyList);
        }
        List<String> currentSet = sets.get(index);
        return currentSet.stream().flatMap(element -> cartesianProduct(sets, index+1)
                .map(list -> {
                    List<String> newList = new ArrayList<>(list);
                    newList.add(0, element);
                    return newList;
                }));
    }
}
