package pt.up.fe.ldts.frogger;

public class PositionRange {
    int yMin;
    int yMax;

    public PositionRange(int min, int max) {
        this.yMin = min;
        this.yMax = max;
    }

    public int getYMin() {
        return yMin;
    }
    public int getYMax() {
        return yMax;
    }
}