import java.util.Map;
import java.util.TreeMap;

public class Node {

    private final Map<String,Integer[]> parameters = new TreeMap<>();
    private String nextNode;

    public Node(String nextNode) {
        this.nextNode = nextNode;
        parameters.put("x",new Integer[]{0,4001});
        parameters.put("m",new Integer[]{0,4001});
        parameters.put("a",new Integer[]{0,4001});
        parameters.put("s",new Integer[]{0,4001});
    }

    public String getNextNode() {
        return nextNode;
    }

    public Integer getFrom(String name) {
        return parameters.get(name)[0];
    }

    public Integer getTo(String name) {
        return parameters.get(name)[1];
    }
    public void setFrom(String name, Integer value) {
        if (value > parameters.get(name)[0]) {
            parameters.get(name)[0] = value;
        }
    }

    public void setTo(String name, Integer value) {
        if (value < parameters.get(name)[1]) {
            parameters.get(name)[1] = value;
        }
    }

    public long getTotal() {
        long total = 1;
        for (String key: parameters.keySet()) {
            total *= (parameters.get(key)[1] - parameters.get(key)[0]-2);
        }
        return total;
    }

    public void copyParametersFrom(Node defNode) {
        setFrom("x", defNode.getFrom("x"));
        setFrom("m", defNode.getFrom("m"));
        setFrom("a", defNode.getFrom("a"));
        setFrom("s", defNode.getFrom("s"));

        setTo("x", defNode.getTo("x"));
        setTo("m", defNode.getTo("m"));
        setTo("a", defNode.getTo("a"));
        setTo("s", defNode.getTo("s"));
    }
}
