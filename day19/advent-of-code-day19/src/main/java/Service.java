import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Service {
    private final List<Workflow> workflows = new ArrayList<>();
    private final Map<String, OperationList> operations = new TreeMap<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            boolean opLines = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    if (opLines) {
                        addOpereation(line);
                    } else {
                        addWorkflow(line);
                    }
                }else {
                    opLines = false;
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private void addOpereation(String line) {
        int end = line.indexOf('{');
        String name = line.substring(0,end);
        String opLine = line.substring(end).substring(1);
        opLine = opLine.substring(0,opLine.indexOf("}"));
        String[] ops = opLine.split(",");
        String otherVise = ops[ops.length-1];
        List<Operation> part = new ArrayList<>();
        for (int i = 0; i < ops.length-1; i++) {
            String opName = ops[i].substring(0,1);
            Character sign = ops[i].substring(1,2).charAt(0);
            int value = Integer.parseInt(ops[i].substring(2, ops[i].indexOf(":")));
            String nextIfTrue = ops[i].substring(
                    ops[i].indexOf(":")+1
            );
            part.add(new Operation(opName, sign, value,nextIfTrue));
        }
        operations.put(name, new OperationList(part,otherVise));
    }

    private void addWorkflow(String line) {
        line = line.substring(1,line.length()-1);
        String[] datas = line.split(",");
        Workflow wf = new Workflow();
        for (String part : datas) {
            wf.addOperation(part.substring(0,1), Integer.parseInt(part.substring(2)));
        }
        workflows.add(wf);
    }

    public void process(Workflow workflow) {
        while (workflow.getStatus() == Status.PROGRESS) {
            String defOperationName = workflow.getNextOperation();
            OperationList operationList = operations.get(defOperationName);
            boolean other = true;
            for (Operation operation : operationList.getOperations()) {
                String name = operation.getName();
                int workflowValue = workflow.getOperationValue(name);
                if (operation.check(workflowValue)) {
                    workflow.setNextOperation(operation.getNextIfTrue());
                    other = false;
                    break;
                }
            }
            if (other) {
                workflow.setNextOperation(operationList.getOtherVise());
            }
        }
    }
    public int partOne() {
        for (Workflow workflow: workflows) {
            process(workflow);
        }
        return workflows.stream()
                .filter(w->w.getStatus() == Status.ACCEPTED)
                .mapToInt(Workflow::getTotalValue)
                .sum();
    }

    public long partTwo() {
        List<Node> nodes = new ArrayList<>();
        List<Node> paths = new ArrayList<>();
        nodes.add(new Node("in"));
        while (!(nodes.size() == 0)) {
            Node defNode = nodes.getFirst();
            OperationList defOperationList = operations.get(defNode.getNextNode());
            Node othervise = new Node(defOperationList.getOtherVise());
            boolean firstOp = true;
            String prevName = "";
            Character prevSign = '=';
            int prevValue = 0;
            for (Operation operation: defOperationList.getOperations()) {
                if (!operation.getNextIfTrue().equals("R")) {
                    Node newNode = new Node(operation.getNextIfTrue());
                    newNode.copyParametersFrom(defNode);
                    if (firstOp) {
                        prevName = operation.getName();
                        prevSign = operation.getSign();
                        prevValue = operation.getValue();
                    } else {
                        if (prevSign == '<') {
                            newNode.setFrom(prevName, prevValue-1);
                            othervise.setFrom(operation.getName(), prevValue+1);
                        } else {
                            newNode.setTo(prevName, prevValue+1);
                            othervise.setTo(operation.getName(), operation.getValue()+1);
                        }
                    }
                    if (operation.getSign() == '<') {
                        newNode.setTo(operation.getName(), operation.getValue());
                        othervise.setFrom(operation.getName(), operation.getValue()-1);
                    } else {
                        newNode.setFrom(operation.getName(), operation.getValue());
                        othervise.setTo(operation.getName(), operation.getValue()+1);
                    }

                    if (operation.getNextIfTrue().equals("A")) {
                        paths.add(newNode);
                    } else {
                        nodes.add(newNode);
                    }
                }
                firstOp = false;
            }
            if (othervise.getNextNode().equals("A")) {
                paths.add(othervise);
            } else if (!(othervise.getNextNode().equals("R"))) {
                nodes.add(othervise);
            }
            nodes.remove(defNode);
        }
        /*long totalAccepted = 0;
        for (int x = 1; x <= 4000; x++) {
            for (int m = 1; m <= 4000; m++) {
                for (int a = 1; a <= 4000; a++) {
                    for (int s = 1; s <= 4000; s++) {
                        Workflow workflow = new Workflow();
                        workflow.addOperation("x",x);
                        workflow.addOperation("m",m);
                        workflow.addOperation("a",a);
                        workflow.addOperation("s",s);
                        process(workflow);
                        if (workflow.getStatus() == Status.ACCEPTED) {
                            totalAccepted += 1;
                        }
                    }
                }
            }
        }
        return totalAccepted;*/
        System.out.println(paths);
        return paths.stream()
                .mapToLong(Node::getTotal)
                .sum();
    }
}
