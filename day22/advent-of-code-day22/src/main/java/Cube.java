public class Cube {
    private Cord start;
    private Cord end;

    public Cube(Cord start, Cord end) {
        this.start = start;
        this.end = end;
    }

    public Cord getStart() {
        return start;
    }

    public Cord getEnd() {
        return end;
    }

    public boolean isCollide(Cube otherCube) {
        //ToDo
        return true;
    }
}
