import java.util.List;
import java.util.Objects;

public class MemoField {
    private String symbols;
    private List<Integer> counts;
    private int groupLoc;

    public MemoField(String symbols, List<Integer> counts, int groupLoc) {
        this.symbols = symbols;
        this.counts = counts;
        this.groupLoc = groupLoc;
    }

    public String getSymbols() {
        return symbols;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public int getGroupLoc() {
        return groupLoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoField memoField)) return false;
        if (getGroupLoc() != ((MemoField) o).getGroupLoc()) {
            return false;
        }
        if (getCounts().size() != ((MemoField) o).getCounts().size()) {
            return false;
        }

        for (int i = 0; i < getCounts().size(); i++) {
            if (!Objects.equals(getCounts().get(i), ((MemoField) o).getCounts().get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbols(), getCounts(), getGroupLoc());
    }
}
