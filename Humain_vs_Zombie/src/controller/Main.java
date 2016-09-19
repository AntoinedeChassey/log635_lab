package controller;

import java.util.List;

import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class Main {

	public static void main(String[] args) {

		Build world = Build.getInstance();

		List<Human> humans = world.getAllHumans();
		List<Item> items = world.getAllItems();
		List<Room> rooms = world.getAllRooms();
		List<Zombie> zombies = world.getAllZombies();

		System.out.println(humans.get(0).getName());
		System.out.println(items.get(0).getName());
		System.out.println(rooms.get(0).getName());
		System.out.println(zombies.get(0).getName());

		// voila on a les datas comme on veut, c'est partit mon kiki
	}
}
