import java.util.Objects;

public class PathData{
    private int heatLost = 0;

    private Cord position = new Cord(0,0);

    private Dir direction = Dir.NONE;

    private int steps = 0;

    public PathData() {
    }

    public PathData(int heatLost, Cord position, Dir direction, int steps) {
        this.heatLost = heatLost;
        this.position = position;
        this.direction = direction;
        this.steps = steps;
    }

    public int getHeatLost() {
        return heatLost;
    }

    public void setHeatLost(int heatLost) {
        this.heatLost = heatLost;
    }

    public Cord getPosition() {
        return position;
    }

    public void setPosition(Cord position) {
        this.position = position;
    }

    public Dir getDirection() {
        return direction;
    }

    public Dir getReverseDirection() {
        if (direction.equals(Dir.UP)) {
            return Dir.DOWN;
        } else if (direction.equals(Dir.DOWN)) {
            return Dir.UP;
        } else if (direction.equals(Dir.LEFT)) {
            return Dir.RIGHT;
        } else if (direction.equals(Dir.RIGHT)) {
            return Dir.LEFT;
        } else {
            return Dir.NONE;
        }
    }

    public void setDirection(Dir direction) {
        this.direction = direction;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PathData pathData)) return false;
        return getHeatLost() == pathData.getHeatLost() && getSteps() == pathData.getSteps() && Objects.equals(getPosition(), pathData.getPosition()) && getDirection() == pathData.getDirection();

    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeatLost(), getPosition(), getDirection(), getSteps());
    }
}
