public class PartNumber {
    public int value;
    public int startXPos;
    public int startYPos;

    public int length;

    public int endXPos;

    public boolean realPart = false;

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

    public boolean isRealPart() {
        return realPart;
    }

    public boolean isCollide(int posX, int row) {
        if ( row == this.startYPos && posX >= this.startXPos && posX <= this.endXPos) {
            setRealPart();
            return true;
        }
        return false;
    }

    public void setRealPart() {
        this.realPart = true;
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
