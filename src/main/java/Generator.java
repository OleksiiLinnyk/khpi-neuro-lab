import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    public static final String TRAIN_DATA_FILE_PATH = "F:\\neurolinks\\src\\main\\resources\\test_data.csv";

    public static void main(String[] args) {
        generateTestData();
    }


    public static void generateTestData() {
        double min = 0;
        double max = 1;


        for (int i = 0; i < 1000; i++) {
            double x1 = min + (max - min) * new Random().nextDouble();
            double x2 = min + (max - min) * new Random().nextDouble();
            double d = defineDParameter(x1, x2);
            Data data = new Data(x1, x2, d);
            writeTrainDataToFile(data);
        }
    }

    private static double defineDParameter(double x1, double x2) {
        double coefficient = 0.5;
        if (x2 < (x1 + coefficient) && x2 > (x1 - coefficient)) {
            return 0;
        }
        return 1;
    }

    public static List<Data> readTrainData() {
        List<Data> trainData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRAIN_DATA_FILE_PATH))) {
            String strCurrentLine;
            while ((strCurrentLine = reader.readLine()) != null) {
                String [] splitedString = strCurrentLine.split(",");
                trainData.add(new Data(Double.parseDouble(splitedString[0]), Double.parseDouble(splitedString[1]), Double.parseDouble(splitedString[2])));
            }
        } catch (Exception ex) {
            System.out.printf("Cannot read a file %s%n", ex.getMessage());
            ex.printStackTrace();
        }
        return trainData;
    }

    public static void writeTrainDataToFile(Data serObj) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRAIN_DATA_FILE_PATH, true))) {
            String [] params = new String[] {String.valueOf(serObj.getX1()), String.valueOf(serObj.getX2()), String.valueOf(serObj.getD())};
            String str = CSVWriter.convertToCSV(params) + "\n";
            writer.write(str);
        } catch (Exception ex) {
            System.out.printf("Cannot write a file %s%n", ex.getMessage());
            ex.printStackTrace();
        }
    }
}
