import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
public class Service {

    private final List<String> sequence = new ArrayList<>();
    private final Map<Integer, List<Lens>> boxes = new TreeMap<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String s: line.split(",")) {
                    sequence.add(s.trim());
                }
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    public int partOne() {
        int total = 0;
        for (String s: sequence) {
            total += calcHash(s);
        }
        return total;
    }

    private int calcHash(String s) {
        int hash = 0;
        for (Character c: s.toCharArray()) {
            hash += c;
            hash *= 17;
            hash %= 256;
        }
        return hash;
    }

    public int partTwo() {
        for (String s: sequence) {
            doOperation(s);
        }
        return calcTotal();
    }

    private int calcTotal() {
        int result = 0;
        for (Map.Entry<Integer, List<Lens>> entrySet: boxes.entrySet()) {
            int box = 1 + entrySet.getKey();
            int total = 0;
            for (int i = 0; i < entrySet.getValue().size(); i++) {
                int slot = 1 + i;
                int focal = entrySet.getValue().get(i).getFocal();
                total += (box * slot * focal);
            }
            result += total;
        }
        return result;
    }

    private void doOperation(String s) {
        String operation;
        int focal = 0;
        String[] datas = s.split("=");
        if (datas.length == 2) {
            operation = "=";
            focal = Integer.parseInt(datas[1]);
        } else {
            operation = "-";
            datas = s.split("-");
        }
        String label = datas[0];
        int boxNumber = calcHash(label);
        List<Lens> oldLenses = boxes.get(boxNumber);
        if (operation.equals("=")) {
            operationEqual(label, focal, boxNumber, oldLenses);
        } else {
            operationDash(boxNumber, oldLenses, label);
        }
    }

    private void operationEqual(String label, int focal, int boxNumber, List<Lens> oldLenses) {
        Lens newLens = new Lens(label, focal);
        if (!boxes.containsKey(boxNumber)) {
            List<Lens> newLenses = new ArrayList<>();
            newLenses.add(newLens);
            boxes.put(boxNumber,newLenses);
        } else {
            int existLens = -1;
            for (int i = 0; i < oldLenses.size(); i++) {
                if (oldLenses.get(i).isSameLabel(newLens)) {
                    existLens = i;
                    break;
                }
            }
            if (existLens != -1) {
                oldLenses.set(existLens, newLens);
            } else {
                oldLenses.add(newLens);
            }
        }
    }

    private void operationDash(int boxNumber, List<Lens> oldLenses, String label) {
        if (!boxes.containsKey(boxNumber)) {
            List<Lens> newLenses = new ArrayList<>();
            boxes.put(boxNumber, newLenses);
        } else {
            int lensToRemove = -1;
            for (int i = 0; i < oldLenses.size(); i++) {
                if (oldLenses.get(i).getLabel().equals(label)) {
                    lensToRemove = i;
                    break;
                }
            }
            if (lensToRemove != -1) {
                oldLenses.remove(lensToRemove);
            }
        }
    }
}