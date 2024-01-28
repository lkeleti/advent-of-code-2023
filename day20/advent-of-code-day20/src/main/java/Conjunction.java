import java.util.*;

public class Conjunction implements LogicModule{

    private String name;
    private final Map<String,Pulses> inputPulses = new HashMap<>();
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
        for (Map.Entry<String,Pulses> entry: inputPulses.entrySet()) {
            if (entry.getValue().equals(Pulses.INIT) || entry.getValue().equals(Pulses.LOW)) {
                status = false;
                break;
            }
        }
        state = !status;
    }
    @Override
    public void setInput(String inputName, Pulses pulse) {
        if (!inputPulses.containsKey(inputName)) {
            inputPulses.put(inputName, pulse);
        }
        inputPulses.put(inputName, pulse);
        if (pulse != Pulses.INIT) {
            updateState();
        }
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
        for (Map.Entry<String,Pulses> entry: inputPulses.entrySet()) {
            if (entry.getValue().equals(Pulses.INIT) || entry.getValue().equals(Pulses.LOW)) {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void resetCounter() {
        low = 0;
        high = 0;
    }

    @Override
    public Object getType() {
        return "Conjunction";
    }

    public Pulses getInput(String inputName) {
        return inputPulses.get(inputName);
    }

    @Override
    public void reset() {
        low = 0;
        high = 0;
        state = false;
        for (Pulses p: inputPulses.values()) {
            p = Pulses.INIT;
        }
    }
}
