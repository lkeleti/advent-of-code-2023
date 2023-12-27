import java.util.Objects;

public class Direction {
    private int posX;
    private int posY;

    public Direction(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return posX == direction.posX && posY == direction.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
