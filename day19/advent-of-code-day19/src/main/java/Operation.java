public class Operation {

    private final String name;
    private final Character sign;
    private final int value;

    private final String nextIfTrue;

    public Operation(String name, Character sign, int value, String nextIfTrue) {
        this.name = name;
        this.sign = sign;
        this.value = value;
        this.nextIfTrue = nextIfTrue;
    }

    public String getName() {
        return name;
    }

    public Character getSign() {
        return sign;
    }

    public int getValue() {
        return value;
    }

    public String getNextIfTrue() {
        return nextIfTrue;
    }

    public boolean check(int otherValue) {
        if (this.sign == '<') {
            return otherValue < this.value;
        } else {
            return otherValue > this.value;
        }
    }
}
