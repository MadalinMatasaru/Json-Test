package site.json.actions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
	static String url = "https://jsonplaceholder.typicode.com/todos/1";

	public static void main(String[] args) {

		JSONObject tomJsonObject = new JSONObject(jsonGetRequest(url));
		String title = tomJsonObject.getString("title");
		boolean completed = tomJsonObject.getBoolean("completed");
		System.out.println(title);

	}

	public boolean checkNodeExists(String nodeName) {
		try {
			new JSONObject(jsonGetRequest(url)).getString(nodeName);
			return true;
		} catch (JSONException e) {
			return false;
		}
	}

	public String getNodeValue(String nodeName) {
		return new JSONObject(jsonGetRequest(url)).getString(nodeName);
	}

	public boolean isJSONValid() {
		String jsonInString = jsonGetRequest(url);
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.readTree(jsonInString);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean getConvertedNodeValue(String nodeName) {
		return new JSONObject(jsonGetRequest(url)).getBoolean(nodeName);
	}

	private static String streamToString(InputStream inputStream) {
		String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
		return text;
	}

	public static String jsonGetRequest(String urlQueryString) {
		String json = null;
		try {
			URL url = new URL(urlQueryString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.connect();
			InputStream inStream = connection.getInputStream();
			json = streamToString(inStream); // input stream to string
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return json;
	}
}
