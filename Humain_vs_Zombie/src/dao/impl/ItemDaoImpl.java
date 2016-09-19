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

			Object obj = parser.parse(new FileReader("resources/config.json"));

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
				Integer combat_points = (int) (long) obj_item.get("combat_points");
				Integer resistance = (int) (long) obj_item.get("resistance");

				Item item = new Item(id_item, id_room, name, combat_points, resistance);
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

//	@Override
//	public Item getItemByRoom(Integer id_room) {
//		Item item = new Item(null, null, null, 0, 0);
//		
//		JSONParser parser = new JSONParser();
//
//		try {
//			Object obj = parser.parse(new FileReader("resources/config.json"));
//
//			JSONObject jsonObject = (JSONObject) obj;
//
//			// get item
//			JSONArray json_array = (JSONArray) jsonObject.get("items");
//			@SuppressWarnings("rawtypes")
//			Iterator i = json_array.iterator();
//
//			while (i.hasNext()) {
//				JSONObject obj_item = (JSONObject) i.next();
//				if (id_room == (int) (long) obj_item.get("id_room")) {
//					Integer id_item = (int) (long) obj_item.get("id_item");
//					String name = (String) obj_item.get("name");
//					Integer combat_points = (int) (long) obj_item.get("combat_points");
//					Integer resistance = (int) (long) obj_item.get("resistance");
//
//					item = new Item(id_item, id_room, name, combat_points, resistance);
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return item;
//	}
}
