public final class Formulas {

    private Formulas() {
    }

    public static double calculateF(double k, double u) {
        return (1.0 / (1.0 + Math.exp(-1 * k * u)));
    }

    public static double calculateFDash(double k, double u) {
        double F = calculateF(k, u);
        return F * k * Math.abs(1.0 - F);
    }
}
