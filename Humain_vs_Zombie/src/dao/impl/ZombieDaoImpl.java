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

import dao.ZombieDao;
import model.Human;
import model.Zombie;

public class ZombieDaoImpl implements ZombieDao {

	@Override
	public List<Zombie> getAllZombies() {
		List<Zombie> zombies = new ArrayList<>();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("resources/facts.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// get zombies
			JSONArray json_array = (JSONArray) jsonObject.get("zombies");
			@SuppressWarnings("rawtypes")
			Iterator i = json_array.iterator();

			while (i.hasNext()) {
				JSONObject obj_zombie = (JSONObject) i.next();
				Integer id_zombie = (int) (long) obj_zombie.get("id_zombie");
				Integer id_room = (int) (long) obj_zombie.get("id_room");
				String name = (String) obj_zombie.get("name");
				Integer life = (int) (long) obj_zombie.get("life");
				Integer size = (int) (long) obj_zombie.get("size");
				Integer aggressivity = (int) (long) obj_zombie.get("aggressivity");

				Zombie zombie = new Zombie(id_zombie, id_room, name, life, size, aggressivity);
				zombies.add(zombie);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return zombies;
	}

	@Override
	public List<Zombie> getZombiesInHumanRoom(List<Zombie> zombies, Human human) {
		List<Zombie> zombiesInHumanRoom = new ArrayList<>();
		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).getId_room() == human.getId_room()) {
				zombiesInHumanRoom.add(zombies.get(i));
			}
			;
		}
		return zombiesInHumanRoom;
	}

	@Override
	public boolean zombieHasNoHumansToEat(List<Zombie> zombies, List<Human> humans) {
		Boolean zombieAlone = true;
		for (int i = 0; i < zombies.size(); i++) {
			for (int j = 0; j < humans.size(); j++) {
				if (zombies.get(i).getId_room() != humans.get(j).getId_room()) {
					zombieAlone = true;
				} else {
					zombieAlone = false;
				}
			}
		}
		return zombieAlone;
	}

}
