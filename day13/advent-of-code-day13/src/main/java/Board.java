import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Character>> table;
    private List<String> rows = new ArrayList<>();
    private List<String> columns = new ArrayList<>();

    public Board(List<List<Character>> table) {
        this.table = table;
        //ToDo create rows and cols
    }

    public String getRow(int rowNumber) {
        return rows.get(rowNumber);
    }

    public String getColumn(int colNumber) {
        return columns.get(colNumber);
    }
}
