import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Output implements LogicModule{
    private Pulses inputPulse;
    private String name;
    private int low;
    private int high;

    public Output(String name) {
        this.name = name;
    }

    @Override
    public void setInput(String inputName, Pulses pulse) {
        inputPulse = pulse;
    }


    public Pulses getInputPulse() {
        return inputPulse;
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
        return low;
    }

    @Override
    public int getHigh() {
        return high;
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
        return "Output";
    }

    @Override
    public void reset() {
        low = 0;
        high = 0;
        inputPulse = Pulses.INIT;
    }
}
