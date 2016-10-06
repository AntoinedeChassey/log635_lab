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

import dao.ItemDao;
import model.Item;

public class ItemDaoImpl implements ItemDao {

	@Override
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<>();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("resources/facts.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// get items
			JSONArray json_array = (JSONArray) jsonObject.get("items");
			@SuppressWarnings("rawtypes")
			Iterator i = json_array.iterator();

			while (i.hasNext()) {
				JSONObject obj_item = (JSONObject) i.next();
				Integer id_item = (int) (long) obj_item.get("id_item");
				Integer id_room = (int) (long) obj_item.get("id_room");
				String name = (String) obj_item.get("name");
				Integer damage = (int) (long) obj_item.get("damage");
				Integer resistance = (int) (long) obj_item.get("resistance");

				Item item = new Item(id_item, id_room, name, damage, resistance);
				items.add(item);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	
	@Override
	public Item getItemByRoomId(List<Item> items, Integer id_room) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId_room() == id_room) {
				return items.get(i);
			}
		}
		return null;
	}
}