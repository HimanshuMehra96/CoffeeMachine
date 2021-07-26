package main.java.input;

import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

public class InputReader {
	public static JSONObject readJsonFile(String resourceName) {
		//String resourceName = "/Input.json";
        InputStream is = InputReader.class.getResourceAsStream(resourceName);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + resourceName);
        }

        JSONTokener tokener = new JSONTokener(is);
        //JSONArray jsonArray = new JSONArray(tokener);
        JSONObject jsonObject = new JSONObject(tokener);
        return jsonObject;
	}
}
