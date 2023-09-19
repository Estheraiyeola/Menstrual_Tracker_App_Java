package data.models;

public class MenstrualCalculator {
    private int id;
    private int cycleLength;
    private MenstrualCalculator details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(int cycleLength) {
        this.cycleLength = cycleLength;
    }

    public void setDetails(MenstrualCalculator details) {
    }
}
