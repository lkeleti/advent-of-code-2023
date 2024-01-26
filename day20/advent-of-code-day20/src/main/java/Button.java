import java.util.Map;
import java.util.Queue;

public class Button implements LogicModule{
    private String name;

    @Override
    public void setInput(String inputName, Pulses pulse) {
        return;
    }

    @Override
    public void execute(Map<String, LogicModule> logicModules, Queue<LogicModule> operationList) {
        return;
    }

    @Override
    public void addOutput(String outputName) {
        return;
    }

    @Override
    public boolean isDefaultSate() {
        return true;
    }

    @Override
    public int getLow() {
        return 1;
    }

    @Override
    public int getHigh() {
        return 0;
    }

    public Button(String name) {
        this.name = name;
    }
}
