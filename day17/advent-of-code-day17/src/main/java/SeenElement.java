import java.util.Objects;

public class SeenElement {
    private Cord position;
    private Dir direction;

    private int steps;

    public SeenElement(Cord position, Dir direction, int steps) {
        this.position = position;
        this.direction = direction;
        this.steps = steps;
    }

    public Cord getPosition() {
        return position;
    }

    public Dir getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeenElement that)) return false;
        return getSteps() == that.getSteps() && Objects.equals(getPosition(), that.getPosition()) && getDirection() == that.getDirection();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getDirection(), getSteps());
    }
}
