public class Lens {
    private final String label;
    private final int focal;

    public Lens(String label, int focal) {
        this.label = label;
        this.focal = focal;
    }

    public String getLabel() {
        return label;
    }

    public int getFocal() {
        return focal;
    }

    public boolean isSameLabel(Lens other) {
        return this.label.equals(other.getLabel());
    }
}
