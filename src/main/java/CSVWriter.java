import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class CSVWriter {

    private String newline = System.getProperty("line.separator");
    private OutputStreamWriter writer = null;

    public CSVWriter(File file, String encoding) throws IOException {
        if (encoding == null) {
            encoding = System.getProperty("file.encoding");
        }
        FileOutputStream fout = new FileOutputStream(file);
        writer = new OutputStreamWriter(fout, encoding);
    }

    //writes header to the file
    public void writeHeader(String[] strings) throws IOException {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] != null) {
                this.writer.write(strings[i]);
                this.writer.write(",");
            }
        }
        this.writer.write(newline);
    }

    //writes data to the file
    public void writeData(List<Text> textList) throws IOException {
        for (int i = 0; i < textList.size(); i++) {
            if (textList.get(i) != null) {
                this.writer.write(textList.get(i).getCipher());
                this.writer.write(",");
                this.writer.write(textList.get(i).getDriverCode());
                this.writer.write(",");
                this.writer.write(textList.get(i).getWaybillCode());
                this.writer.write(",");
                this.writer.write(Boolean.toString(textList.get(i).isInsecure()));
                this.writer.write(",");
                this.writer.write(Boolean.toString(textList.get(i).isFragile()));
                this.writer.write(",");
                this.writer.write(textList.get(i).getTemperature());
                this.writer.write(",");
                this.writer.write(textList.get(i).getName());
                this.writer.write(",");
            }
            this.writer.write(newline);
        }
    }

    //closes OutputStreamWriter
    public void close() {
        try {
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
