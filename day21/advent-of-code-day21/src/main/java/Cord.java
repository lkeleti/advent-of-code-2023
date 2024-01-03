import java.util.Objects;

public class Cord {
    private Long posX;
    private Long posY;

    public Cord(Long posX, Long posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Integer getPosX() {
        return posX.intValue();
    }

    public Integer getPosY() {
        return posY.intValue();
    }

    public Long getPosXLong() {
        return posX;
    }

    public Long getPosYLong() {
        return posY;
    }

    public Cord addDirection(Direction direction) {
        return new Cord(posX + direction.getCord().getPosX(), posY + direction.getCord().getPosY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cord cord)) return false;
        return Objects.equals(getPosX(), cord.getPosX()) && Objects.equals(getPosY(), cord.getPosY());
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

    public Cord interpolate(int boardWith, int boardHeight) {
        return new Cord(Math.abs(posX % boardWith), Math.abs(posY % boardHeight));
    }
}
