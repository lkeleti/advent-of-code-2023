public enum Dir {

    UP(0,-1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0), NONE(0,0);

    private final Cord direction;

    Dir(int posX, int posY) {
        this.direction = new Cord(posX, posY);
    }

    public Cord getDirection() {
        return direction;
    }
}
