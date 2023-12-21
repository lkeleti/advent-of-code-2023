import java.util.List;

public class OperationList {
    private final List<Operation> operations;
    private final String otherVise;

    public OperationList(List<Operation> operations, String otherVise) {
        this.operations = operations;
        this.otherVise = otherVise;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public String getOtherVise() {
        return otherVise;
    }
}
