public enum Direction {

    UP(new Cord(0,-1)),LEFT(new Cord(-1,0)),DOWN(new Cord(0,1)),RIGHT(new Cord(1,0));

    private Cord cord;

    Direction(Cord cord) {
        this.cord = cord;
    }

    public Cord getCord() {
        return cord;
    }
}
