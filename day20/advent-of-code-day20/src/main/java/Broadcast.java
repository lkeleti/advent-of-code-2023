import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Broadcast implements LogicModule{
    private String name;
    private Pulses pulse;
    private final List<String> outputNames = new ArrayList<>();

    private int high = 0;
    private int low = 0;

    @Override
    public void setInput(String inputName, Pulses pulse) {
        this.pulse = pulse;
    }

    @Override
    public void execute(Map<String, LogicModule> logicModules, Queue<LogicModule> operationList) {
        for (String moduleName : outputNames) {
            LogicModule defModule = logicModules.get(moduleName);
            defModule.setInput(this.name, this.pulse);
            operationList.add(defModule);
            low++;
        }
    }

    public Broadcast(String name) {
        this.name = name;
    }

    public void addOutput(String outputName) {
        outputNames.add(outputName);
    }

    @Override
    public boolean isDefaultSate() {
        return true;
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
