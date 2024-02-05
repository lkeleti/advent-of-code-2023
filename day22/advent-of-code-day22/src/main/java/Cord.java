import java.util.Objects;

public class Cord {
    private int posX;
    private int posY;

    private int posZ;

    public Cord(int posX, int posY, int posZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosZ() {
        return posZ;
    }

    public void setPosZ(int posZ) {
        this.posZ = posZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cord cord)) return false;
        return getPosX() == cord.getPosX() && getPosY() == cord.getPosY() && getPosZ() == cord.getPosZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosX(), getPosY(), getPosZ());
    }

    @Override
    public String toString() {
        return "Cord{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", posZ=" + posZ +
                '}';
    }
}
