import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CSVWriter {


    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(CSVWriter::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
