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

public class FactsManager {

	private static FactsManager instance;

	public static FactsManager getInstance() {
		if (instance == null) {
			instance = new FactsManager();
		}
		return instance;
	}

	private HumanDao humanDao = new HumanDaoImpl();
	private ItemDao itemDao = new ItemDaoImpl();
	private RoomDao roomDao = new RoomDaoImpl();
	private ZombieDao zombieDao = new ZombieDaoImpl();

	private FactsManager() {
	}

	// Human methods

	public List<Human> getAllHumans() {
		return humanDao.getAllHumans();
	}

	public Human getHumanByName(List<Human> humans, String name) {
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

	public List<Zombie> getZombiesInHumanRoom(List<Zombie> zombies, Human human) {
		return zombieDao.getZombiesInHumanRoom(zombies, human);
	}

	public boolean zombieHasNoHumansToEat(List<Zombie> zombies, List<Human> humans) {
		return zombieDao.zombieHasNoHumansToEat(zombies, humans);
	}
}
