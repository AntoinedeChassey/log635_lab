package dao.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dao.HumanDao;
import model.Human;

public class HumanDaoImpl implements HumanDao {

	@Override
	public List<Human> getAllHumans() {
		List<Human> humans = new ArrayList<>();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("resources/config.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// get humans
			JSONArray json_array = (JSONArray) jsonObject.get("humans");
			@SuppressWarnings("rawtypes")
			Iterator i = json_array.iterator();

			while (i.hasNext()) {
				JSONObject obj_human = (JSONObject) i.next();
				Integer id_human = (int) (long) obj_human.get("id_human");
				Integer id_room = (int) (long) obj_human.get("id_room");
				String name = (String) obj_human.get("name");
				Integer life = (int) (long) obj_human.get("life");
				Integer age = (int) (long) obj_human.get("age");
				Integer size = (int) (long) obj_human.get("size");
				Integer weight = (int) (long) obj_human.get("weight");
				Integer combat_capacity = (int) (long) obj_human.get("combat_capacity");

				Human human = new Human(id_human, id_room, name, life, age, size, weight, combat_capacity);
				humans.add(human);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return humans;
	}

}
