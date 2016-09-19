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

public class Build {

	private static Build instance;

	public static Build getInstance() {
		if (instance == null) {
			instance = new Build();
		}
		return instance;
	}

	private HumanDao humanDao = new HumanDaoImpl();
	private ItemDao itemDao = new ItemDaoImpl();
	private RoomDao roomDao = new RoomDaoImpl();
	private ZombieDao zombieDao = new ZombieDaoImpl();

	private Build() {
	}

	// Human methods

	public List<Human> getAllHumans() {
		return humanDao.getAllHumans();
	}

	// Item methods

	public List<Item> getAllItems() {
		return itemDao.getAllItems();
	}

	// Room methods

	public List<Room> getAllRooms() {
		return roomDao.getAllRooms();
	}

	// Zombie methods

	public List<Zombie> getAllZombies() {
		return zombieDao.getAllZombies();
	}
}
