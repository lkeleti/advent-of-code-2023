public enum Direction {

    UP(0,-1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0);

    private Cord cord;

    Direction(int posX, int posY) {
        this.cord = new Cord(posX, posY);
    }

    public Cord getCord() {
        return cord;
    }
}
