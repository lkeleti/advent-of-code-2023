import java.util.Objects;

public class Cord {
    private int posX;
    private int posY;

    public Cord(int posX, int posY) {
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
        Cord cord = (Cord) o;
        return posX == cord.posX && posY == cord.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    public Cord add(Direction nextDir) {
        return new Cord(this.getPosX() + nextDir.getCord().getPosX(), this.getPosY() + nextDir.getCord().getPosY());
    }
}
