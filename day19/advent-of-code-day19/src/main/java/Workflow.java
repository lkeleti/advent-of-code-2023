import java.util.Map;
import java.util.TreeMap;

public class Workflow {
    private String nextOperation = "in";
    private final Map<String, Integer> values = new TreeMap<>();
    private Status status = Status.PROGRESS;

    private int totalValue = 0;

    public void addOperation(String name, int value) {
        values.put(name, value);
        this.totalValue += value;
    }

    public  int getOperationValue(String name) {
        if ( values.containsKey(name)) {
            return values.get(name);
        }
        throw new IllegalArgumentException("Invalid operation name!");
    }
    public String getNextOperation() {
        return nextOperation;
    }


    public Status getStatus() {
        return status;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setNextOperation(String nextOperation) {
        this.nextOperation = nextOperation;
        if (nextOperation.equals("A")) {
            this.status = Status.ACCEPTED;
        } else if (nextOperation.equals("R")) {
            this.status = Status.REJECTED;
        }
    }
}
