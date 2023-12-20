import java.util.Objects;

public class Cord {
    private long posX;
    private long posY;

    public Cord(long posX, long posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public long getPosX() {
        return posX;
    }

    public long getPosY() {
        return posY;
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
}

