public class Game {
    private int duration;
    private int distance;

    public Game(int duration) {
        this.duration = duration;
    }

    public Game(int duration, int distance) {
        this.duration = duration;
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Game{" +
                "duration=" + duration +
                ", distance=" + distance +
                '}';
    }
}
