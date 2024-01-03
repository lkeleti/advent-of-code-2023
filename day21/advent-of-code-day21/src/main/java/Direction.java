public enum Direction {

    UP(new Cord(0L,-1L)),LEFT(new Cord(-1L,0L)),DOWN(new Cord(0L,1L)),RIGHT(new Cord(1L,0L));

    private Cord cord;

    Direction(Cord cord) {
        this.cord = cord;
    }

    public Cord getCord() {
        return cord;
    }
}
