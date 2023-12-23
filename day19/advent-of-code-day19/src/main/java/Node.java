public class Node {
    private int xFrom = 4000;
    private int xTo = 4000;
    private int mFrom = 4000;
    private int mTo = 4000;
    private int sFrom = 4000;
    private int sTo = 4000;
    private int aFrom = 4000;
    private int aTo = 4000;

    private String nextNode;

    public Node(String nextNode) {
        this.nextNode = nextNode;
    }

    public int getxFrom() {
        return xFrom;
    }

    public void setxFrom(int xFrom) {
        this.xFrom = xFrom;
    }

    public int getxTo() {
        return xTo;
    }

    public void setxTo(int xTo) {
        this.xTo = xTo;
    }

    public int getmFrom() {
        return mFrom;
    }

    public void setmFrom(int mFrom) {
        this.mFrom = mFrom;
    }

    public int getmTo() {
        return mTo;
    }

    public void setmTo(int mTo) {
        this.mTo = mTo;
    }

    public int getsFrom() {
        return sFrom;
    }

    public void setsFrom(int sFrom) {
        this.sFrom = sFrom;
    }

    public int getsTo() {
        return sTo;
    }

    public void setsTo(int sTo) {
        this.sTo = sTo;
    }

    public int getaFrom() {
        return aFrom;
    }

    public void setaFrom(int aFrom) {
        this.aFrom = aFrom;
    }

    public int getaTo() {
        return aTo;
    }

    public void setaTo(int aTo) {
        this.aTo = aTo;
    }

    public String getNextNode() {
        return nextNode;
    }
}
