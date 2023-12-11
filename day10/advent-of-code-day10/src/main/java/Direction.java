import java.util.Objects;

public class Direction {
    private int nextCol;
    private int nextRow;

    public Direction(int nextCol, int nextRow) {
        this.nextCol = nextCol;
        this.nextRow = nextRow;
    }

    public int getNextCol() {
        return nextCol;
    }

    public int getNextRow() {
        return nextRow;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "nextCol=" + nextCol +
                ", nextRow=" + nextRow +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return nextCol == direction.nextCol && nextRow == direction.nextRow;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextCol, nextRow);
    }
}
