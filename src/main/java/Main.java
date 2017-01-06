import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String []args) throws IOException {
        String fileName = "C://Users/user/Desktop/new/file.csv";
        File file = new File(fileName);
        CSVWriter wr = new CSVWriter(file, "UTF-8");
        String[] header = new String[]{"cipher", "driverCode", "waybillCode", "isInsecure", "isFragile", "temperature", "name"};
        wr.writeHeader(header);
        List<String> readText = new TextReader().read();
        TextParser tp = new TextParser();
        List<Text> parsedText = tp.parseText(readText);
        wr.writeData(parsedText);
        wr.close();
    }
}