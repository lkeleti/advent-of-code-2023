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
                inputLines.add(line);
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
            setOutputs();
            resetCounters();
            Button button = new Button("button");
            LogicModule broadcast = logicModules.get("broadcaster");
            button.addOutput(broadcast.getName());
            logicModules.put(button.getName(), button);

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private void setOutputs() {
        for (String l: inputLines) {
            String name = l.substring(0, l.indexOf(" ")).replaceAll("%","").replaceAll("&","");
            String outputs = l.substring(l.indexOf(" ") + 4);
            for (String outputName: outputs.split(",")) {
                outputName = outputName.replaceAll(" ", "");
                LogicModule lm = logicModules.get(outputName);
                if (lm != null && lm.getType().equals("Conjunction")) {
                    lm.setInput(name, Pulses.INIT);
                } else if (lm == null) {
                    logicModules.put(outputName,new Output(outputName));
                }
            }
        }
    }

    private void resetCounters() {
        for (LogicModule lm: logicModules.values()) {
            lm.resetCounter();
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
        LogicModule button = logicModules.get("button");
        for (int i = 0; i < 1000; i++) {
            int firstRuns = 0;
            operationList.add(button);
            while (!(operationList.isEmpty() || (firstRuns > 2 && checkAllStateIsDefault()))) {
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
        //818649769 good
        return low*high;
    }

    public long partTwo() {
       operationList.clear();
        resetModules();
        LogicModule button = logicModules.get("button");
        boolean cycle = true;
        long counter = 1;
        long kk = 0;
        long sk = 0;
        long xc = 0;
        long vt = 0;

        while (cycle) {
            int firstRuns = 0;
            operationList.add(button);
            while (!(operationList.isEmpty() || (firstRuns > 2 && checkAllStateIsDefault()))) {
                operationList.poll().execute(logicModules, operationList);
                firstRuns++;
                //kk;sk;xc;vt
                if (operationList.peek() != null && operationList.peek().getName().equals("tj") && ((Conjunction)operationList.peek()).getInput("kk").equals(Pulses.HIGH)){
                    kk = counter;
                }
                if (operationList.peek() != null && operationList.peek().getName().equals("tj") && ((Conjunction)operationList.peek()).getInput("sk").equals(Pulses.HIGH)){
                    sk = counter;
                }
                if (operationList.peek() != null && operationList.peek().getName().equals("tj") && ((Conjunction)operationList.peek()).getInput("xc").equals(Pulses.HIGH)){
                    xc = counter;
                }
                if (operationList.peek() != null && operationList.peek().getName().equals("tj") && ((Conjunction)operationList.peek()).getInput("vt").equals(Pulses.HIGH)){
                    vt = counter;
                }
                if (kk != 0 && sk != 0 && xc != 0 && vt != 0){
                    cycle = false;
                    break;
                }
            }
            counter++;
        }
        return lcm( new ArrayList<>(List.of(kk,sk,xc,vt)));
    }

    private void resetModules() {
        for (LogicModule lm: logicModules.values()) {
            lm.reset();
        }
    }

    private long lcm(List<Long> numbers) {
        long result = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == 0) {
                    return 0;
                }
                else if (numbers.get(i) < 0) {
                    numbers.set(i, numbers.get(i) * (-1));
                }
                if (numbers.get(i) == 1) {
                    counter++;
                }

                if (numbers.get(i) % divisor == 0) {
                    divisible = true;
                    numbers.set(i, numbers.get(i) / divisor);
                }
            }

            if (divisible) {
                result = result * divisor;
            }
            else {
                divisor++;
            }

            if (counter == numbers.size()) {
                return result;
            }
        }
    }
}
