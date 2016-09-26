package controller;

import java.util.List;

import dao.HumanDao;
import dao.ItemDao;
import dao.RoomDao;
import dao.ZombieDao;
import dao.impl.HumanDaoImpl;
import dao.impl.ItemDaoImpl;
import dao.impl.RoomDaoImpl;
import dao.impl.ZombieDaoImpl;
import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class Manager {

	private static Manager instance;

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	private HumanDao humanDao = new HumanDaoImpl();
	private ItemDao itemDao = new ItemDaoImpl();
	private RoomDao roomDao = new RoomDaoImpl();
	private ZombieDao zombieDao = new ZombieDaoImpl();

	private Manager() {
	}

	// Human methods

	public List<Human> getAllHumans() {
		return humanDao.getAllHumans();
	}
	
	public Human getHumanByName(List<Human> humans, String name){
		return humanDao.getHumanByName(humans, name);
	}
	

	// Item methods

	public List<Item> getAllItems() {
		return itemDao.getAllItems();
	}
	
	public Item getItemByRoomId(List<Item> items, Integer id_room) {
		return itemDao.getItemByRoomId(items, id_room);
	}

	// Room methods

	public List<Room> getAllRooms() {
		return roomDao.getAllRooms();
	}
	
	public Room getRoomById(List<Room> rooms, Integer id_room) {
		return roomDao.getRoomById(rooms, id_room);
	}

	// Zombie methods

	public List<Zombie> getAllZombies() {
		return zombieDao.getAllZombies();
	}
}
