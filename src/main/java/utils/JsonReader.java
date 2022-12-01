package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    public static Object[][] getJSONData(String JSON_path, String JSON_data, int JSON_attribute) throws IOException, ParseException {
        Object object = new JSONParser().parse(new FileReader(JSON_path));
        JSONObject jo = (JSONObject) object;
        JSONArray js = (JSONArray) jo.get(JSON_data);

        Object[][] arr = new String[js.size()][JSON_attribute];
        for (int i = 0; i < js.size(); i++) {
            JSONObject obj1 = (JSONObject) js.get(i);
            arr[i][0] = String.valueOf(obj1.get("login"));
            arr[i][1] = String.valueOf(obj1.get("pass"));
        }
        return arr;
    }
}
