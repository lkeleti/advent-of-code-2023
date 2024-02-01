import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cube implements Comparable<Cube>{
    private List<Cord> points = new ArrayList<>();
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
        calcPoints();
    }

    private void calcPoints() {
        points.clear();
        for (int i = left; i <= right; i++) {
            for (int j = front; j <= back; j++) {
                for (int k = bottom; k <= top; k++) {
                    points.add(
                            new Cord(
                                    i,j,k
                            )
                    );
                }
            }
        }
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

    public List<Cord> getPoints() {
        return points;
    }

    public int getRight() {
        return right;
    }

    public void moveDown() {
        start.setPosZ(start.getPosZ()-1);
        end.setPosZ(end.getPosZ()-1);
        bottom--;
        top--;
        calcPoints();
    }

    public void moveUp() {
        start.setPosZ(start.getPosZ()+1);
        end.setPosZ(end.getPosZ()+1);
        bottom++;
        top++;
        calcPoints();
    }
    public boolean isCollide(Cube otherCube) {
        //boolean xCollision = right >= otherCube.getLeft() && left <= otherCube.getRight();
        //boolean yCollision = top >= otherCube.getBottom() && bottom <= otherCube.getTop();
        //boolean zCollision = front >= otherCube.getBack() && back <= otherCube.getFront();

        //return xCollision && yCollision && zCollision;

        for (Cord point: points) {
            if (otherCube.getPoints().contains(point)) {
                return false;
            }
        }
        return true;
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
