import java.util.Optional;

public class MapTable {

    private final long sourceStart;
    private final long sourceEnd;

    private final long destinationStart;

    public MapTable(long destinationStart, long sourceStart, long range ) {
        this.sourceStart = sourceStart;
        this.sourceEnd = sourceStart + range - 1;
        this.destinationStart = destinationStart;
    }

    public Optional<Long> getDestination(long source) {
        if (source >= sourceStart && source <= sourceEnd) {
            return Optional.of((source - sourceStart) + destinationStart);
        } else {
            return Optional.empty();
        }
    }
}
