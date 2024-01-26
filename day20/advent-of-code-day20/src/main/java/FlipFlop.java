import java.util.*;

public class FlipFlop implements LogicModule{

    private String name;
    private final Queue<Pulses> inputPulses = new ArrayDeque<>();
    private final List<String> outputNames = new ArrayList<>();

    private boolean state = false;

    private int high = 0;
    private int low = 0;

    public FlipFlop(String name) {
        this.name = name;
    }

    public void addOutput(String outputName) {
        outputNames.add(outputName);
    }
    @Override
    public void setInput(String inputName, Pulses pulse) {
        inputPulses.add(pulse);
    }
    @Override
    public void execute(Map<String,LogicModule> logicModules, Queue<LogicModule> operationList) {
        if (inputPulses.poll().equals(Pulses.LOW)) {
            state = !state;
            for (String moduleName : outputNames) {
                LogicModule defModule = logicModules.get(moduleName);
                defModule.setInput(this.name, state?Pulses.HIGH:Pulses.LOW);
                operationList.add(defModule);
                if (state) {
                    high++;
                } else {
                    low++;
                }
            }
        }
    }

    @Override
    public boolean isDefaultSate() {
        return !state;
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
