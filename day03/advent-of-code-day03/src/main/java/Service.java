import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Service {
    private final List<List<String>> board = new ArrayList<>();
    private int boardSize = 0;
    private int boardRowCount = 0;
    private final List<PartNumber> partNumbers = new ArrayList<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> row = new ArrayList<>();
                for (char c: line.toCharArray()) {
                    row.add(String.valueOf(c));
                }
                board.add(row);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
        boardSize = board.get(0).size();
        boardRowCount = board.size();
        getPartNumbers();
    }

    private void getPartNumbers() {
        for (int rowNumber = 0; rowNumber < boardRowCount; rowNumber++) {
            List<String> row = board.get(rowNumber);
            getNumberFromRow(row, rowNumber);
        }
    }

    private void getNumberFromRow(List<String> row, int rowNumber) {
        boolean startedNumeric = false;
        int startPos = -1;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            String c = row.get(i);
            if (isNumeric(c)) {
                if (!startedNumeric) {
                    startedNumeric = true;
                    startPos = i;
                    number.append(c);
                } else {
                    number.append(c);
                }
            } else {
                if (startedNumeric) {
                    partNumbers.add(new PartNumber(Integer.parseInt(number.toString()),startPos, rowNumber));
                    startedNumeric = false;
                    number = new StringBuilder();
                }
            }
        }
    }

    private boolean isNumeric(String c) {
        String reg = "^\\d+$";
        return Pattern.matches(reg,c);
    }

    private long checkCollision() {
        for (int i = 0; i < boardRowCount; i++) {
            for (int j = 0; j < boardSize; j++) {
                String sign = board.get(i).get(j);
                if (!isNumeric(sign) && !sign.equals(".")) {
                    isCollide(j,i);
                }
            }
        }
        System.out.println(partNumbers);
        return countPartNumbers();
    }

    private int countPartNumbers() {
        int sum = 0;
        for (PartNumber partNumber: partNumbers) {
            if (partNumber.isRealPart()) {
                sum += partNumber.getValue();
            }
        }
        return sum;
    }

    private void isCollide(int posX, int posY) {
        //left
        if (posX > 0) {
            checkPartNumbers(posX - 1, posY);
        }

        //right
        if (posX < boardSize) {
            checkPartNumbers(posX + 1, posY);
        }

        //top
        if (posY > 0) {
            checkPartNumbers(posX, posY - 1);
        }

        //bottom
        if (posY < boardRowCount) {
            checkPartNumbers(posX, posY + 1);
        }

        //top-left
        if (posY > 0 && posX > 0) {
            checkPartNumbers(posX - 1, posY - 1);
        }

        //top-right
        if (posY > 0 && posX < boardSize) {
            checkPartNumbers(posX + 1, posY - 1);
        }

        //bottom-left
        if (posY < boardRowCount && posX > 0) {
            checkPartNumbers(posX - 1, posY + 1);
        }

        //bottom-right
        if (posY < boardRowCount && posX < boardSize) {
            checkPartNumbers(posX + 1, posY + 1);
        }
    }

    public void checkPartNumbers(int posX, int posY) {
        for (PartNumber partNumber: partNumbers) {
            partNumber.isCollide(posX, posY);
        }
    }

    public long partOne() {
        return checkCollision();
    }

    public int partTwo() {
        return 0;
    }
    //560570 low
    //564167 high
}
