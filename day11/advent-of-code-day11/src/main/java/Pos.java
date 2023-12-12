public class Pos {
    private final int posX;
    private final int posY;

    public Pos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int calcDistance(Pos otherPos) {
        return Math.abs(this.posX - otherPos.getPosX()) + Math.abs(this.posY - otherPos.getPosY());
    }
}
