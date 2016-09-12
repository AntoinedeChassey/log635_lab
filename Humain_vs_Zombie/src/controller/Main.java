package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("resources/config.json"));

			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(obj);

			// String name = (String) jsonObject.get("human");
			// System.out.println(name);

			JSONArray humans = (JSONArray) jsonObject.get("humans");
			Iterator<JSONObject> iterator = humans.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().get("name"));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
