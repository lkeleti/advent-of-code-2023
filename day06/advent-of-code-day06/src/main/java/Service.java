import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final List<Game> games = new ArrayList<>();
    public void readInput(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(":")[1].split(" ");
                int i = 0;
                for (String data: datas) {
                    if (!data.isEmpty()) {
                        if (line.contains("Time:")) {
                            games.add(new Game(Integer.parseInt(data)));
                        } else {
                            games.get(i).setDistance(Integer.parseInt(data));
                        }
                        i++;
                    }
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    public int partOne() {
        int sum = 1;
        for (Game game: games) {
            sum *= countWays(game);
        }
        return sum;
    }

    private int countWays(Game game) {
        int distance = game.getDistance();
        int time = game.getDuration();
        int counter = 0;
        for (int speed = 1; speed < time; speed++) {
            int defDistance = speed * (time - speed);
            if (defDistance > distance) {
                counter++;
            }
        }
        return counter;
    }

    public long partTwo() {
        StringBuilder distancePart = new StringBuilder();
        StringBuilder timePart = new StringBuilder();
        for (Game game: games) {
            distancePart.append(game.getDistance());
            timePart.append(game.getDuration());
        }
        long distance = Long.parseLong(distancePart.toString());
        long time = Long.parseLong(timePart.toString());
        long counter = 0;
        for (long speed = 1; speed < time; speed++) {
            long defDistance = speed * (time - speed);
            if (defDistance > distance) {
                counter++;
            }
        }
        return counter;
    }
}
