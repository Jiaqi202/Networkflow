package preflowpush;

public class PVertexData {

    private int height;
    private double excess;

    public PVertexData() {
        height = 0;
        excess = 0.0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getExcess() {
        return excess;
    }

    public void setExcess(double excess) {
        this.excess = excess;
    }
}
