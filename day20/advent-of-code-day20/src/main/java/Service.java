import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Service {

    private final Map<String,LogicModule> logicModules = new HashMap<>();
    private final Queue<LogicModule> operationList = new ArrayDeque<>();
    private final  List<String> inputLines = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                inputLines.add(line);
                String name;
                if (line.charAt(0) == '%') {
                    name = line.substring(1, line.indexOf(" "));
                    logicModules.put(name, new FlipFlop(name));
                } else if (line.charAt(0) == '&') {
                    name = line.substring(1, line.indexOf(" "));
                    logicModules.put(name, new Conjunction(name));
                } else {
                    name = line.substring(0, line.indexOf(" "));
                    logicModules.put(name, new Broadcast(name));
                }
                String outputs = line.substring(line.indexOf(" ") + 4);
                for (String outputName: outputs.split(",")) {
                    logicModules.get(name).addOutput(outputName.replaceAll(" ",""));
                }
            }
            logicModules.put("button", new Button("button"));
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private boolean checkAllStateIsDefault() {
        for (LogicModule lm: logicModules.values()) {
            if (!lm.isDefaultSate()) {
                return false;
            }
        }
        return true;
    }
    public int partOne() {
        for (int i = 0; i < 1000; i++) {
            int firstRuns = 0;
            LogicModule broadcast = logicModules.get("broadcaster");
            broadcast.setInput("button", Pulses.LOW);
            operationList.add(broadcast);
            while (!(operationList.isEmpty() || (firstRuns > 1 && checkAllStateIsDefault()))) {
                operationList.poll().execute(logicModules, operationList);
                firstRuns++;
            }
        }
        return countLowAndHigh();
    }

    private int countLowAndHigh() {
        int low = 0;
        int high = 0;
        for (LogicModule lm: logicModules.values()){
            low += lm.getLow();
            high += lm.getHigh();
        }
        return low*high;
    }

    public int partTwo() {
        return 0;
    }
}

