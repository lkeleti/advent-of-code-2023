import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MemoField {
    private String springs;
    private List<Integer> counts;

    public MemoField(String springs, List<Integer> counts) {
        this.springs = springs;
        this.counts = counts;
    }

    public String getSprings() {
        return springs;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoField memoField)) return false;

        if (!this.springs.equals(((MemoField) o).getSprings())) {
            return false;
        }

        if (getCounts().size() != ((MemoField) o).getCounts().size()) {
            return false;
        }

        for (int i = 0; i < getCounts().size(); i++) {
            if (this.counts.get(i) != ((MemoField) o).getCounts().get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSprings(), getCounts());
    }
}
