import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextReader {
    public List<String> readText = new ArrayList<String>();

    //reads the source text
    public List<String> read(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String str;
            while((str = br.readLine()) != null && str.length() != 0) {
                readText.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readText;
    }
}
