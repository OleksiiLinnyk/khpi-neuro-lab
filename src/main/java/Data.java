import java.io.Serializable;

public class Data implements Serializable {
    private double x1;

    private double x2;

    private double d;

    private double y;
    private double epsilon;

    public Data() {
    }

    public Data(double x1, double x2, double d) {
        this.x1 = x1;
        this.x2 = x2;
        this.d = d;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    @Override
    public String toString() {
        return "Data{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", d=" + d +
                '}';
    }
}
