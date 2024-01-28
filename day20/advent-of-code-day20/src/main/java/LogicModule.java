import java.util.Map;
import java.util.Queue;

public interface LogicModule {
    void setInput(String inputName, Pulses pulse);
    void execute(Map<String,LogicModule> logicModules, Queue<LogicModule> operationList);
    void addOutput(String outputName);

    boolean isDefaultSate();

    int getLow();
    int getHigh();

    String getName();

    void resetCounter();

    Object getType();

    void reset();


}
