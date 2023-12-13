import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private final List<List<Character>> table;
    private final List<String> rows = new ArrayList<>();
    private final List<String> columns = new ArrayList<>();

    public Board(List<List<Character>> table) {
        this.table = table;

        for (List<Character> row: table) {
            rows.add(row.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining()));
        }

        for (int i = 0; i < table.getFirst().size(); i++) {
            StringBuilder col = new StringBuilder();
            for (List<Character> characters : table) {
                col.append(characters.get(i).toString());
            }
            columns.add(col.toString());
        }
    }

    public String getRow(int rowNumber) {
        return rows.get(rowNumber);
    }

    public String getColumn(int colNumber) {
        return columns.get(colNumber);
    }

    public int getRowSize() {
        return rows.size();
    }
    public int getColSize() {
        return columns.size();
    }
}
