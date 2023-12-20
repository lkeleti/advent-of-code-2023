import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        service.readInput(Path.of("src/main/resources/input.txt"));
        System.out.println("Answer of part 1:");
        System.out.println(service.partOne());
        System.out.println("Answer of part 2:");
        System.out.println(service.partTwo());

           int[] xCoordinates = {0, 6, 6, 4, 4, 6, 6, 1, 1, 0, 0, 2, 2, 0, 0};
            int[] yCoordinates = {0, 0, 5, 5, 7, 7, 9, 9, 7, 7, 5, 5, 2, 2, 0};

            double area = calculatePolygonArea(xCoordinates, yCoordinates);

            System.out.println("Area of the polygon: " + area);

    }

    public static double calculatePolygonArea(int[] xCoordinates, int[] yCoordinates) {
        int n = xCoordinates.length;
        if (n != yCoordinates.length || n < 3) {
            throw new IllegalArgumentException("Invalid number of coordinates");
        }

        double area = 0.0;
        for (int i = 0; i < n - 1; i++) {
            area += (xCoordinates[i] * yCoordinates[i + 1]) - (xCoordinates[i + 1] * yCoordinates[i]);
        }
        area += (xCoordinates[n - 1] * yCoordinates[0]) - (xCoordinates[0] * yCoordinates[n - 1]);

        area = 0.5 * Math.abs(area);
        return area;
    }

}
