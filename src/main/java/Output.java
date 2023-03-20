public class Output {

    private double trainEpsilonSquareSum = 0;
    private double testEpsilonSquareSum = 0;

    public void increaseTrain(double f) {
        trainEpsilonSquareSum += f;
    }

    public void increaseTest(double f) {
        testEpsilonSquareSum += f;
    }

    public double getTrainE() {
        return trainEpsilonSquareSum / 2;
    }

    public double getTestE() {
        return testEpsilonSquareSum / 2;
    }
}
