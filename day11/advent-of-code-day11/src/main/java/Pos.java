public class Pos {
    private final long posX;
    private final long posY;

    public Pos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public long getPosX() {
        return posX;
    }

    public long getPosY() {
        return posY;
    }

    public long calcDistance(Pos otherPos) {
        return Math.abs(this.posX - otherPos.getPosX()) + Math.abs(this.posY - otherPos.getPosY());
    }
}
