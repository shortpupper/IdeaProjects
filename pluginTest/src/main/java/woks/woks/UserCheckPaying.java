package woks.woks;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserCheckPaying {
    public static Object CheckPaying() {
        List<String> names = new ArrayList<>();
        try {
            URL url = new URL("https://raw.githubusercontent.com/shortpupper/MineCraft/main/payingSubs.json");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            String lines = "";
            while ((inputLine = in.readLine()) != null) {
                lines += (inputLine);
            }
            in.close();
            JsonObject jsonObject = (JsonObject) JsonParser.parseString(lines);
            JsonArray properties = jsonObject.get("Paying").getAsJsonArray();
            int propertiesLength = jsonObject.get("PayingLength").getAsInt();
            for (int i = 0; i < propertiesLength; i++) {
                names.add(properties.get(i).getAsString());
            }
            return names;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
