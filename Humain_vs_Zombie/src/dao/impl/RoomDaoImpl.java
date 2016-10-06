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

import dao.RoomDao;
import model.Room;

public class RoomDaoImpl implements RoomDao {

	@Override
	public List<Room> getAllRooms() {
		List<Room> rooms = new ArrayList<>();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("resources/facts.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// get rooms
			JSONArray json_array = (JSONArray) jsonObject.get("rooms");
			@SuppressWarnings("rawtypes")
			Iterator i = json_array.iterator();

			while (i.hasNext()) {
				JSONObject obj_room = (JSONObject) i.next();
				Integer id_room = (int) (long) obj_room.get("id_room");
				String name = (String) obj_room.get("name");
				Integer size = (int) (long) obj_room.get("size");
				Integer light = (int) (long) obj_room.get("light");

				Room room = new Room(id_room, name, size, light);
				rooms.add(room);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rooms;
	}
	
	@Override
	public Room getRoomById(List<Room> rooms, Integer id_room){
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getId_room() == id_room) {
				return rooms.get(i);
			}
		}
		return null;
	}
}
