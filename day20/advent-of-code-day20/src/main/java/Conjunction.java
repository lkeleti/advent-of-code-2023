import java.util.*;

public class Conjunction implements LogicModule{

    private String name;
    private final Map<String,Queue<Pulses>> inputPulses = new HashMap<>();
    private final List<String> outputNames = new ArrayList<>();

    private int high = 0;
    private int low = 0;
    private boolean state = false;
    public Conjunction(String name) {
        this.name = name;
    }

    public void addOutput(String outputName) {
        outputNames.add(outputName);
    }

    private void updateState() {
        boolean status = true;
        for (Map.Entry<String,Queue<Pulses>> entry: inputPulses.entrySet()) {
            if (entry.getValue().isEmpty() || entry.getValue().peek().equals(Pulses.LOW)) {
                status = false;
                break;
            }
        }
        state = !status;
    }
    @Override
    public void setInput(String inputName, Pulses pulse) {
        if (!inputPulses.containsKey(inputName)) {
            inputPulses.put(inputName, new ArrayDeque<>());
        }
        inputPulses.get(inputName).add(pulse);
        updateState();
    }

    @Override
    public void execute(Map<String, LogicModule> logicModules, Queue<LogicModule> operationList) {
        boolean isAllHigh = isAllHigh();
        for (String moduleName : outputNames) {
            LogicModule defModule = logicModules.get(moduleName);

            if (isAllHigh) {
                low++;
                state = false;
            } else {
                state = true;
                high++;
            }

            if (defModule != null) {
                defModule.setInput(this.name, isAllHigh ? Pulses.LOW : Pulses.HIGH);
                operationList.add(defModule);
            }
        }
    }

    private boolean isAllHigh() {
        boolean isAllHigh = true;
        for (Map.Entry<String,Queue<Pulses>> entry: inputPulses.entrySet()) {
            if (entry.getValue().isEmpty() || entry.getValue().poll().equals(Pulses.LOW)) {
                isAllHigh = false;
            }
        }
        return isAllHigh;
    }

    @Override
    public boolean isDefaultSate() {
        return  !state;
    }

    @Override
    public int getHigh() {
        return high;
    }

    @Override
    public int getLow() {
        return low;
    }
}
