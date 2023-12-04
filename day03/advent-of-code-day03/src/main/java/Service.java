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
        long sum = 0;
        for (PartNumber part: partNumbers) {
            boolean collide = false;
            for (int i = 0; i < part.getLength(); i++) {
                if (isCollide(part.getStartXPos() + i, part.getStartYPos())) {
                    sum += part.getValue();
                    break;
                }
            }
        }
        return sum;
    }
    private boolean isCollide(int posX, int posY) {
        //left
        if (posX > 0) {
            String leftChar = board.get(posY).get(posX-1);
            if (!isNumeric(leftChar) && !leftChar.equals(".")) {
                return true;
            }
        }

        //right
        if (posX < boardSize) {
            String rightChar = board.get(posY).get(posX+1);
            if (!isNumeric(rightChar) && !rightChar.equals(".")) {
                return true;
            }
        }

        //top
        if (posY > 0) {
            String topChar = board.get(posY-1).get(posX);
            if (!isNumeric(topChar) && !topChar.equals(".")) {
                return true;
            }
        }
        //bottom
        if (posY < boardRowCount-1) {
            String bottomChar = board.get(posY+1).get(posX);
            if (!isNumeric(bottomChar) && !bottomChar.equals(".")) {
                return true;
            }
        }

        //top-left
        if (posY > 0 && posX > 0) {
            String topLeftChar = board.get(posY-1).get(posX-1);
            if (!isNumeric(topLeftChar) && !topLeftChar.equals(".")) {
                return true;
            }
        }

        //top-right
        if (posY > 0 && posX < boardSize) {
            String topRightChar = board.get(posY-1).get(posX+1);
            if (!isNumeric(topRightChar) && !topRightChar.equals(".")) {
                return true;
            }
        }

        //bottom-left
        if (posY < boardRowCount-1 && posX > 0) {
            String bottomLeftChar = board.get(posY+1).get(posX-1);
            if (!isNumeric(bottomLeftChar) && !bottomLeftChar.equals(".")) {
                return true;
            }
        }

        //bottom-right
        if (posY < boardRowCount-1 && posX < boardSize) {
            String bottomRightChar = board.get(posY+1).get(posX+1);
            if (!isNumeric(bottomRightChar) && !bottomRightChar.equals(".")) {
                return true;
            }
        }
        return false;
    }

    public long partOne() {
        return checkCollision();
    }

    public int partTwo() {
        return 0;
    }
    //560570 low
}
