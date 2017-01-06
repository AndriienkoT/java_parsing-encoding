import java.util.ArrayList;
import java.util.List;

public class TextParser {
    public List<Text> parsedText = new ArrayList<Text>();

    //parses the text
    public List<Text> parseText(List<String> readText){

        for(String s : readText){
            Text t = new Text();

            t.setCipher(s); //set cipher
            t.setDriverCode(s.substring(0, 4)); //set driver code
            s = s.substring(4);
            int indexR = -1;
            indexR = s.toLowerCase().indexOf('r');
            s = s.substring(indexR); //cut the string till 'r' or 'R'
            int indexDriverCode = -1;
            indexDriverCode = s.indexOf(t.getDriverCode());
            String wayBillCode;
            boolean hasD = false, hasF = false;
            if(indexDriverCode != -1) { //if driver code doesn't repeat in string
                wayBillCode = s.substring(0, indexDriverCode + 4);
                if(s.substring(1).startsWith("d") || s.substring(2).startsWith("d")) //if the string contains 'd'
                    hasD = true;
                if(s.substring(1).startsWith("f") || s.substring(2).startsWith("f")) //if the string contains 'f'
                    hasF = true;
                s = s.substring(indexDriverCode + 4);
            }else { //if driver code repeats in string
                int wayBillCodeLen = 1;
                if(s.substring(1).startsWith("d") || s.substring(2).startsWith("d")) { //if the string contains 'd'
                    hasD = true;
                    wayBillCodeLen++;
                }
                if(s.substring(1).startsWith("f") || s.substring(2).startsWith("f")) { ////if the string contains 'f'
                    hasF = true;
                    wayBillCodeLen++;
                }
                if(wayBillCodeLen > 1) {
                    wayBillCodeLen += 3;
                }else {
                    wayBillCodeLen += 4;
                }
                if (wayBillCodeLen == 5) {
                    wayBillCode = s.substring(0, 5);
                    s = s.substring(5);
                }else{
                    wayBillCode = s.substring(0, 6);
                    s = s.substring(6);
                }
            }
            t.setWaybillCode(wayBillCode); //set waybill code
            t.setInsecure(hasD); //set whether is insecure
            t.setFragile(hasF); //set whether is fragile

            if(s.startsWith("+") || Character.isDefined(s.charAt(0))){
                t.setTemperature(s.substring(0, 4)); //set temperature
                s = s.substring(4);
            }else{
                t.setTemperature(""); //set temperature
            }

            List<String> strArr = new ArrayList<String>();
            //divide number code of 'name' for 3 digits
            for (int i = 0; i < s.length(); i = i + 3){
                if(i + 3 <= s.length())
                    strArr.add(s.substring(i, i + 3));
            }

            String name = "";
            //encode from octal to metric and from metric to ASCII
            for(String str : strArr){
                StringBuilder sb = new StringBuilder();
                int metric = Integer.parseInt(str, 8);
                sb.append(metric);
                String nextChar = String.copyValueOf(Character.toChars(Integer.parseInt(sb.toString())));
                name += nextChar;
            }
            t.setName(name); //set name
            parsedText.add(t); //add the instance of Text to the list
        }
        return parsedText;
    }
}
