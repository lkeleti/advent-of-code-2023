public class Instruct {
    private Character direction;
    private Integer distance;
    private String color;

    public Instruct(Character direction, Integer distance, String color) {
        this.direction = direction;
        this.distance = distance;
        this.color = color;
    }

    public Character getDirection() {
        return direction;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getColor() {
        return color;
    }
}
