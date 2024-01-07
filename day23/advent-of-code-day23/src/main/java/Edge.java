import java.util.Objects;

public class Edge {
    private final Cord startNode;
    private final Cord endNode;
    private final int length;

    public Edge(Cord startNode, Cord endNode, int length) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.length = length;
    }

    public Cord getStartNode() {
        return startNode;
    }

    public Cord getEndNode() {
        return endNode;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge edge)) return false;
        return (getLength() == edge.getLength() && Objects.equals(getStartNode(), edge.getStartNode()) && Objects.equals(getEndNode(), edge.getEndNode()) ||
                getLength() == edge.getLength() && Objects.equals(getEndNode(), edge.getStartNode()) && Objects.equals(getStartNode(), edge.getEndNode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartNode(), getEndNode(), getLength());
    }

    @Override
    public String toString() {
        return "Edge{" +
                "startNode=" + startNode +
                ", endNode=" + endNode +
                ", length=" + length +
                '}';
    }
}
