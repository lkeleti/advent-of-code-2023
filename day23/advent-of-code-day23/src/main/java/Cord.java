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

    public Cord addCord(Cord otherCord) {
        return new Cord(posX + otherCord.getPosX(), posY + otherCord.getPosY());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cord cord)) return false;
        return getPosX() == cord.getPosX() && getPosY() == cord.getPosY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosX(), getPosY());
    }

    @Override
    public String toString() {
        return "Cord{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
