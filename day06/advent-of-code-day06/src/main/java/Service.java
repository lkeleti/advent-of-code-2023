import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Game> games = new ArrayList<>();
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
        System.out.println(games);
        return 0;
    }
    public int partTwo() {
        return 0;
    }
}
