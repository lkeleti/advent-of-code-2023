import java.util.Map;
import java.util.Queue;

public class Button implements LogicModule{
    private String name;
    private String output;

    private int low;

    @Override
    public void setInput(String inputName, Pulses pulse) {
        return;
    }

    @Override
    public void execute(Map<String, LogicModule> logicModules, Queue<LogicModule> operationList) {
        logicModules.get(output).setInput(name,Pulses.LOW);
        operationList.add(logicModules.get(output));
        low++;
    }

    @Override
    public void addOutput(String outputName) {
        output = outputName;
    }

    @Override
    public boolean isDefaultSate() {
        return true;
    }

    @Override
    public int getLow() {
        return low;
    }

    @Override
    public int getHigh() {
        return 0;
    }

    public Button(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void resetCounter() {
        low = 0;
    }

    @Override
    public Object getType() {
        return "Button";
    }

    @Override
    public void reset() {
        low = 0;
    }
}
