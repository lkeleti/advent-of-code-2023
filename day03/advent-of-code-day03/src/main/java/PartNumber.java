public class PartNumber {
    public int value;
    public int startXPos;
    public int startYPos;

    public int length;

    public int endXPos;

    public PartNumber(int value, int startXPos, int startYPos) {
        this.value = value;
        this.startXPos = startXPos;
        this.startYPos = startYPos;
        this.length = String.valueOf(value).length();
        this.endXPos = startXPos + length;
    }

    public int getValue() {
        return value;
    }

    public int getStartXPos() {
        return startXPos;
    }

    public int getStartYPos() {
        return startYPos;
    }

    public int getLength() {
        return length;
    }

    public int getEndXPos() {
        return endXPos;
    }

    @Override
    public String toString() {
        return "PartNumber{" +
                "value=" + value +
                ", startXPos=" + startXPos +
                ", startYPos=" + startYPos +
                '}';
    }
}
