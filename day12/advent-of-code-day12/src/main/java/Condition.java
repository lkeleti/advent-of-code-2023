import java.util.List;
import java.util.Objects;

public class Condition {
    private String springs;

    private List<Integer> groups;

    public Condition(String springs, List<Integer> groups) {
        this.springs = springs;
        this.groups = groups;
    }

    public String getSprings() {
        return springs;
    }

    public List<Integer> getGroups() {
        return groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Condition condition)) return false;
        if (!getSprings().equals(((Condition) o).getSprings())) {
            return false;
        }
        if (getGroups().size() != ((Condition) o).getGroups().size()) {
            return false;
        }

        for (int i = 0; i < getGroups().size(); i++) {
            if (!Objects.equals(getGroups().get(i), ((Condition) o).getGroups().get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSprings(), getGroups());
    }
}
