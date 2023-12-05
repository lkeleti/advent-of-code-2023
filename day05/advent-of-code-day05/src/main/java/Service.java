import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Service {

    private List<Long> seeds = new ArrayList<>();
    private List<MapTable> seedToSoil = new ArrayList<>();
    private List<MapTable> soilToFertilizer = new ArrayList<>();
    private List<MapTable> fertilizerToWater = new ArrayList<>();
    private List<MapTable> waterToLight = new ArrayList<>();
    private List<MapTable> lightToTemperature = new ArrayList<>();
    private List<MapTable> temperatureToHumidity = new ArrayList<>();
    private List<MapTable> humidityToLocation = new ArrayList<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            String status = "";
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    if (!line.contains(":")) {
                        if (status.equals("seed-to-soil map:")) {
                            seedToSoil.add(getMapFromLine(line));
                        }

                        if (status.equals("soil-to-fertilizer map:")) {
                            soilToFertilizer.add(getMapFromLine(line));
                        }

                        if (status.equals("fertilizer-to-water map:")) {
                            fertilizerToWater.add(getMapFromLine(line));
                        }

                        if (status.equals("water-to-light map:")) {
                            waterToLight.add(getMapFromLine(line));
                        }

                        if (status.equals("light-to-temperature map:")) {
                            lightToTemperature.add(getMapFromLine(line));
                        }

                        if (status.equals("temperature-to-humidity map:")) {
                            temperatureToHumidity.add(getMapFromLine(line));
                        }

                        if (status.equals("humidity-to-location map:")) {
                            humidityToLocation.add(getMapFromLine(line));
                        }
                    }

                    if (line.contains("seeds:")) {
                        getSeedsFromLine(line);
                    } else if (line.contains("seed-to-soil map:")) {
                        status = "seed-to-soil map:";
                    } else if (line.contains("soil-to-fertilizer map:")) {
                        status = "soil-to-fertilizer map:";
                    } else if (line.contains("fertilizer-to-water map:")) {
                        status = "fertilizer-to-water map:";
                    } else if (line.contains("water-to-light map:")) {
                        status = "water-to-light map:";
                    } else if (line.contains("light-to-temperature map:")) {
                        status = "light-to-temperature map:";
                    } else if (line.contains("temperature-to-humidity map:")) {
                        status = "temperature-to-humidity map:";
                    } else if (line.contains("humidity-to-location map:")) {
                        status = "humidity-to-location map:";
                    }
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }

    private MapTable getMapFromLine(String line) {
        String[] datas = line.split(" ");
        return new MapTable(Long.parseLong(datas[0]), Long.parseLong(datas[1]), Long.parseLong(datas[2]));
    }

    private void getSeedsFromLine(String line) {
        String[] seedsArray = line.split(":")[1].split(" ");
        for (String seed: seedsArray) {
            if (!seed.isEmpty()) {
                seeds.add(Long.parseLong(seed));
            }
        }
    }

    public long partOne() {
        long lowestLocation = Long.MAX_VALUE;
        for (Long seed: seeds) {
            long soil = getSeedToSoil(seed);
            long fertilizer = getSoilToFertilizer(soil);
            long water = getFertilizerToWater(fertilizer);
            long light = getWaterToLight(water);
            long temperature = getLightToTemperature(light);
            long humidity = getTemperatureToHumidity(temperature);
            long location = getHumidityToLocation(humidity);

            if (location < lowestLocation) {
                lowestLocation = location;
            }
        }
        return lowestLocation;
    }

    private long getSeedToSoil(Long seed) {
        for (MapTable map: seedToSoil) {
            Optional<Long> result = map.getDestination(seed);
            if (result.isPresent()) {
                return result.get();
            }
        }
        return seed;
    }

    private long getSoilToFertilizer(Long soil) {
        for (MapTable map: soilToFertilizer) {
            Optional<Long> result = map.getDestination(soil);
            if (result.isPresent()) {
                return result.get();
            }
        }
        return soil;
    }

    private long getFertilizerToWater(Long fertilizer) {
        for (MapTable map: fertilizerToWater) {
            Optional<Long> result = map.getDestination(fertilizer);
            if (result.isPresent()) {
                return result.get();
            }
        }
        return fertilizer;
    }

    private long getWaterToLight(Long water) {
        for (MapTable map: waterToLight) {
            Optional<Long> result = map.getDestination(water);
            if (result.isPresent()) {
                return result.get();
            }
        }
        return water;
    }

    private long getLightToTemperature(Long light) {
        for (MapTable map: lightToTemperature) {
            Optional<Long> result = map.getDestination(light);
            if (result.isPresent()) {
                return result.get();
            }
        }
        return light;
    }

    private long getTemperatureToHumidity(Long temperature) {
        for (MapTable map: temperatureToHumidity) {
            Optional<Long> result = map.getDestination(temperature);
            if (result.isPresent()) {
                return result.get();
            }
        }
        return temperature;
    }

    private long getHumidityToLocation(Long humidity) {
        for (MapTable map: humidityToLocation) {
            Optional<Long> result = map.getDestination(humidity);
            if (result.isPresent()) {
                return result.get();
            }
        }
        return humidity;
    }

    public long partTwo() {
        long lowestLocation = Long.MAX_VALUE;
        for ( int i =0; i < seeds.size(); i+=2) {
            long startSeed = seeds.get(i);
            long lastSeed = startSeed + seeds.get(i + 1);
            for (long seed = startSeed; seed < lastSeed; seed++) {
                long soil = getSeedToSoil(seed);
                long fertilizer = getSoilToFertilizer(soil);
                long water = getFertilizerToWater(fertilizer);
                long light = getWaterToLight(water);
                long temperature = getLightToTemperature(light);
                long humidity = getTemperatureToHumidity(temperature);
                long location = getHumidityToLocation(humidity);

                if (location < lowestLocation) {
                    lowestLocation = location;
                }
            }
        }
        return lowestLocation;
    }
}
