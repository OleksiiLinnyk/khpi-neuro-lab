import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final double k = 0.3;
    private static final double eta = 0.3;
    private static final int eras = 30_000;

    private static WeightCoefficient weightCoefficient = new WeightCoefficient();
    private static final List<Data> testDatas = new ArrayList<>();

    private static final String EPSILON_FILE = "src/main/resources/epsilon.csv";
    private static final String WEIGHTS_FILE_NAME = "src/main/resources/weights.csv";

    public static void main(String[] args) {
        List<Data> trainDatas = Generator.readTrainData();
        Data data00 = new Data(0f, 0f, 0);
        Data data01 = new Data(0f, 1f, 1);
        Data data10 = new Data(1f, 0f, 1);
        Data data11 = new Data(1f, 1f, 0);

        testDatas.add(data00);
        testDatas.add(data01);
        testDatas.add(data10);
        testDatas.add(data11);

        for (int i = 1; i <= eras; i++) {
            Output output = new Output();
            for (Data trainData : trainDatas) {
                double epsilon = calculateEntry(trainData, true);
                output.increaseTrain(Math.pow(epsilon, 2));
            }
            for (Data entry : testDatas) {
                double epsilon = calculateEntry(entry, false);
                output.increaseTest(Math.pow(epsilon, 2));
            }

            appendFile(EPSILON_FILE, parseResult(output, trainDatas.size(), testDatas.size(), i));
            appendFile(WEIGHTS_FILE_NAME, parseWeights(weightCoefficient, i));
        }
    }

    private static void appendFile(String fileName, String[] params) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String param = CSVWriter.convertToCSV(params) + "\n";
            writer.write(param);
        } catch (Exception ex) {
            System.out.printf("Cannot write a file %s%n", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static double calculateEntry(Data data, boolean recalculateWeights) {
        double u11 = weightCoefficient.getW_1_10() * 1 + weightCoefficient.getW_1_11() * data.getX1() + weightCoefficient.getW_1_12() * data.getX2();
        double u12 = weightCoefficient.getW_1_20() * 1 + weightCoefficient.getW_1_21() * data.getX1() + weightCoefficient.getW_1_22() * data.getX2();
        double y11 = Formulas.calculateF(k, u11);
        double y12 = Formulas.calculateF(k, u12);
        double u21 = weightCoefficient.getW_2_10() * 1 + weightCoefficient.getW_2_11() * y11 + weightCoefficient.getW_2_12() * y12;

        data.setY(Formulas.calculateF(k, u21));
        data.setEpsilon(data.getD() - data.getY());

        if (recalculateWeights) {
            weightCoefficient.setW_2_10(weightCoefficient.getW_2_10() + (eta * data.getEpsilon() * Formulas.calculateFDash(k, u21)));
            weightCoefficient.setW_2_11(weightCoefficient.getW_2_11() + (eta * data.getEpsilon() * Formulas.calculateFDash(k, u21) * y11));
            weightCoefficient.setW_2_12(weightCoefficient.getW_2_12() + (eta * data.getEpsilon() * Formulas.calculateFDash(k, u21) * y12));

            weightCoefficient.setW_1_10(weightCoefficient.getW_1_10() + (eta * data.getEpsilon() * Formulas.calculateFDash(k, u21)
                    * weightCoefficient.getW_2_11() * Formulas.calculateFDash(k, u11)));
            weightCoefficient.setW_1_11(weightCoefficient.getW_1_11() + (eta * data.getEpsilon() * Formulas.calculateFDash(k, u21)
                    * weightCoefficient.getW_2_11() * Formulas.calculateFDash(k, u11) * data.getX1()));
            weightCoefficient.setW_1_12(weightCoefficient.getW_1_12() + (eta * data.getEpsilon() * Formulas.calculateFDash(k, u21)
                    * weightCoefficient.getW_2_11() * Formulas.calculateFDash(k, u11) * data.getX2()));

            weightCoefficient.setW_1_20(weightCoefficient.getW_1_20() + (eta * data.getEpsilon() * Formulas.calculateFDash(k, u21)
                    * weightCoefficient.getW_2_12() * Formulas.calculateFDash(k, u12)));
            weightCoefficient.setW_1_21(weightCoefficient.getW_1_21() + (eta * data.getEpsilon() * Formulas.calculateFDash(k, u21)
                    * weightCoefficient.getW_2_12() * Formulas.calculateFDash(k, u12) * data.getX1()));
            weightCoefficient.setW_1_22(weightCoefficient.getW_1_22() + (eta * data.getEpsilon() * Formulas.calculateFDash(k, u21)
                    * weightCoefficient.getW_2_12() * Formulas.calculateFDash(k, u12) * data.getX2()));
        }

        return data.getEpsilon();
    }

    private static String[] parseResult(Output output, int kTrain, int kTest, int age) {
        double trainE = output.getTrainE();
        double trainEAverage = trainE / kTrain;
        double testE = output.getTestE();
        double testEAverage = testE / kTest;

        return new String[]{"era-" + age, String.valueOf(trainE), String.valueOf(trainEAverage),
                String.valueOf(testE), String.valueOf(testEAverage)};
    }

    private static String[] parseWeights(WeightCoefficient weightCoefficient, int era) {
        return new String[]{
                "era-" + era,
                String.valueOf(weightCoefficient.getW_1_10()),
                String.valueOf(weightCoefficient.getW_1_11()),
                String.valueOf(weightCoefficient.getW_1_12()),
                String.valueOf(weightCoefficient.getW_1_20()),
                String.valueOf(weightCoefficient.getW_1_21()),
                String.valueOf(weightCoefficient.getW_1_22()),
                String.valueOf(weightCoefficient.getW_2_10()),
                String.valueOf(weightCoefficient.getW_2_11()),
                String.valueOf(weightCoefficient.getW_2_12()),
        };
    }
}