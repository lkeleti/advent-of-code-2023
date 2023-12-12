import java.util.List;

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
}
