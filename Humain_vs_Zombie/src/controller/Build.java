package controller;

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

import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class Build {

	private List<Human> humans = new ArrayList<>();
	private List<Item> items = new ArrayList<>();
	private List<Room> rooms = new ArrayList<>();
	private List<Zombie> zombies = new ArrayList<>();

	public Build() {

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("resources/config.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// get humans
			JSONArray json_humans = (JSONArray) jsonObject.get("humans");
			@SuppressWarnings("rawtypes")
			Iterator i0 = json_humans.iterator();

			while (i0.hasNext()) {
				JSONObject obj_human = (JSONObject) i0.next();
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

			// get items
			JSONArray json_items = (JSONArray) jsonObject.get("items");
			@SuppressWarnings("rawtypes")
			Iterator i1 = json_items.iterator();

			while (i1.hasNext()) {
				JSONObject obj_item = (JSONObject) i1.next();
				Integer id_item = (int) (long) obj_item.get("id_item");
				Integer id_room = (int) (long) obj_item.get("id_room");
				String name = (String) obj_item.get("name");
				Integer combat_points = (int) (long) obj_item.get("combat_points");
				Integer resistance = (int) (long) obj_item.get("resistance");

				Item item = new Item(id_item, id_room, name, combat_points, resistance);
				items.add(item);
			}

			// get rooms
			JSONArray json_rooms = (JSONArray) jsonObject.get("rooms");
			@SuppressWarnings("rawtypes")
			Iterator i2 = json_rooms.iterator();

			while (i2.hasNext()) {
				JSONObject obj_room = (JSONObject) i2.next();
				Integer id_room = (int) (long) obj_room.get("id_room");
				String name = (String) obj_room.get("name");
				Integer size = (int) (long) obj_room.get("size");
				Integer light = (int) (long) obj_room.get("light");

				Room room = new Room(id_room, name, size, light);
				rooms.add(room);
			}

			// get zombies
			JSONArray json_zombies = (JSONArray) jsonObject.get("zombies");
			@SuppressWarnings("rawtypes")
			Iterator i3 = json_zombies.iterator();

			while (i3.hasNext()) {
				JSONObject obj_zombie = (JSONObject) i3.next();
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
	}

	public List<Human> getHumans() {
		return humans;
	}

	public void setHumans(List<Human> humans) {
		this.humans = humans;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<Zombie> getZombies() {
		return zombies;
	}

	public void setZombies(List<Zombie> zombies) {
		this.zombies = zombies;
	}
	
	
}
