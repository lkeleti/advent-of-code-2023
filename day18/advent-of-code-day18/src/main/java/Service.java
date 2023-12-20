import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Instruct> instructions = new ArrayList<>();
    private List<Cord> polygon = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(" ");
                instructions.add(new Instruct(datas[0].toCharArray()[0], Integer.parseInt(datas[1]),datas[2]));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private long calcArea() {
        int i;
        int j;

        double area = 0.0;
        for (i = 0; i < polygon.size()-1; i++) {
            j = i+1;

            area += ((polygon.get(i).getPosX()* polygon.get(j).getPosY()) - (polygon.get(j).getPosX()*polygon.get(i).getPosY()));
        }
        area /= 2.0;
        return (long)Math.abs(area);
    }

    private void createPolygon() {
        long defX = 0;
        long defY = 0;
        polygon.add(new Cord(defX,defY));
        for (Instruct i: instructions) {
            int direction = i.getDirection();
            int distance = i.getDistance();

            switch (direction) {
                case 'U':
                    defY -= distance;
                    break;
                case 'D':
                    defY+= distance;
                    break;
                case 'L':
                    defX -= distance;
                    break;
                case 'R':
                    defX += (distance);
                    break;
            }
            polygon.add(new Cord(defX,defY));
        }
    }
    public long partOne() {
        createPolygon();
        long area = calcArea();
        //34329
        return area + instructions.stream().mapToInt(Instruct::getDistance).sum() /2 + 1 ;
    }

    private long createBigPolygon() {
        long defX = 0;
        long defY = 0;
        long boundary = 0;
        polygon.add(new Cord(defX,defY));
        for (Instruct i: instructions) {
            String color = i.getColor();
            char direction = color.toCharArray()[7];
            long distance = Integer.parseInt(color.substring(2,7),16);
            boundary += distance;

            switch (direction) {
                case '3':
                    defY -= distance;
                    break;
                case '1':
                    defY+= distance;
                    break;
                case '2':
                    defX -= distance;
                    break;
                case '0':
                    defX += (distance);
                    break;
            }
            polygon.add(new Cord(defX,defY));
        }
        return boundary;
    }
    public long partTwo() {
        polygon.clear();
        long boundary = createBigPolygon();
        long area = calcArea();
        //34329
        //42617884106796 low
        return area + boundary /2 + 1 ;
    }
}
