package dao;

import java.util.List;

import model.Item;

public interface ItemDao {

	public List<Item> getAllItems();

	public Item getItemByRoomId(List<Item> items, Integer id_room);
	
}
