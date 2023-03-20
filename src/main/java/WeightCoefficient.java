public class WeightCoefficient {

    private double w_1_10 = 0.1;
    private double w_1_11 = -0.3;
    private double w_1_12 = 0.4;

    private double w_1_20 = -0.7;
    private double w_1_21 = -0.1;
    private double w_1_22 = 0.01;

    private double w_2_10 = 0.4;
    private double w_2_11 = -0.2;
    private double w_2_12 = 0.1;

    public WeightCoefficient(double w_1_10, double w_1_11, double w_1_12,
                             double w_1_20, double w_1_21, double w_1_22,
                             double w_2_10, double w_2_11, double w_2_12) {
        this.w_1_10 = w_1_10;
        this.w_1_11 = w_1_11;
        this.w_1_12 = w_1_12;
        this.w_1_20 = w_1_20;
        this.w_1_21 = w_1_21;
        this.w_1_22 = w_1_22;
        this.w_2_10 = w_2_10;
        this.w_2_11 = w_2_11;
        this.w_2_12 = w_2_12;
    }

    public WeightCoefficient() {
    }

    public double getW_1_10() {
        return w_1_10;
    }

    public void setW_1_10(double w_1_10) {
        this.w_1_10 = w_1_10;
    }

    public double getW_1_11() {
        return w_1_11;
    }

    public void setW_1_11(double w_1_11) {
        this.w_1_11 = w_1_11;
    }

    public double getW_1_12() {
        return w_1_12;
    }

    public void setW_1_12(double w_1_12) {
        this.w_1_12 = w_1_12;
    }

    public double getW_1_20() {
        return w_1_20;
    }

    public void setW_1_20(double w_1_20) {
        this.w_1_20 = w_1_20;
    }

    public double getW_1_21() {
        return w_1_21;
    }

    public void setW_1_21(double w_1_21) {
        this.w_1_21 = w_1_21;
    }

    public double getW_1_22() {
        return w_1_22;
    }

    public void setW_1_22(double w_1_22) {
        this.w_1_22 = w_1_22;
    }

    public double getW_2_10() {
        return w_2_10;
    }

    public void setW_2_10(double w_2_10) {
        this.w_2_10 = w_2_10;
    }

    public double getW_2_11() {
        return w_2_11;
    }

    public void setW_2_11(double w_2_11) {
        this.w_2_11 = w_2_11;
    }

    public double getW_2_12() {
        return w_2_12;
    }

    public void setW_2_12(double w_2_12) {
        this.w_2_12 = w_2_12;
    }

    @Override
    public String toString() {
        return "WeightCoefficient{" +
                "w_1_10=" + w_1_10 +
                ", w_1_11=" + w_1_11 +
                ", w_1_12=" + w_1_12 +
                ", w_1_20=" + w_1_20 +
                ", w_1_21=" + w_1_21 +
                ", w_1_22=" + w_1_22 +
                ", w_2_10=" + w_2_10 +
                ", w_2_11=" + w_2_11 +
                ", w_2_12=" + w_2_12 +
                '}';
    }
}
