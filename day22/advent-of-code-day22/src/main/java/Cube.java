import java.util.Objects;

public class Cube implements Comparable<Cube>{
    private Cord start;
    private Cord end;

    private int top;
    private int bottom;
    private int front;
    private int back;
    private int left;
    private int right;

    public Cube(Cord start, Cord end) {
        this.start = start;
        this.end = end;
        top = Math.max(start.getPosZ(), end.getPosZ());
        bottom = Math.min(start.getPosZ(), end.getPosZ());
        right = Math.max(start.getPosX(), end.getPosX());
        left = Math.min(start.getPosX(), end.getPosX());
        back = Math.max(start.getPosY(), end.getPosY());
        front = Math.min(start.getPosY(), end.getPosY());
    }

    public Cord getStart() {
        return start;
    }

    public Cord getEnd() {
        return end;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getFront() {
        return front;
    }

    public int getBack() {
        return back;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public boolean isCollide(Cube otherCube) {
        boolean xCollision = right >= otherCube.getLeft() && left <= otherCube.getRight();
        boolean yCollision = top >= otherCube.getBottom() && bottom <= otherCube.getTop();
        boolean zCollision = front >= otherCube.getBack() && back <= otherCube.getFront();

        return xCollision && yCollision && zCollision;
    }

    @Override
    public int compareTo(Cube o) {
        return bottom - o.getBottom();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cube cube)) return false;
        return getTop() == cube.getTop() && getBottom() == cube.getBottom() && getFront() == cube.getFront() && getBack() == cube.getBack() && getLeft() == cube.getLeft() && getRight() == cube.getRight() && Objects.equals(getStart(), cube.getStart()) && Objects.equals(getEnd(), cube.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd(), getTop(), getBottom(), getFront(), getBack(), getLeft(), getRight());
    }
}
