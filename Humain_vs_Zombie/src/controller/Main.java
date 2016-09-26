package controller;

import java.util.List;

import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class Main {

	public static void main(String[] args) {

		Manager world = Manager.getInstance();

		List<Human> humans = world.getAllHumans();
		List<Item> items = world.getAllItems();
		List<Room> rooms = world.getAllRooms();
		List<Zombie> zombies = world.getAllZombies();

		System.out.println("Vie des humains au début:");
		for (int i = 0; i < humans.size(); i++) {
			System.out.println("\t" + humans.get(i).getLife());
		}

		System.out.println("Vie des zombies au début:");
		for (int i = 0; i < zombies.size(); i++) {
			System.out.println("\t" + zombies.get(i).getLife());
		}
		System.out.println("Lancement du scénario...\n\n");

		new Rule(humans, items, rooms, zombies);

		System.out.println("\n\nVie des humains à la fin:");
		for (int i = 0; i < humans.size(); i++) {
			System.out.println("\t" + humans.get(i).getName() +": " + humans.get(i).getLife());
		}

		System.out.println("Vie des zombies à la fin:");
		for (int i = 0; i < zombies.size(); i++) {
			System.out.println("\t" + zombies.get(i).getName() +": " + zombies.get(i).getLife());
		}

	}
}
