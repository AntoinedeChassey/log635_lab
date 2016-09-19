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

		// for (int i = 0; i < humans.size(); i++) {
		// System.out.println(humans.get(i).getLife());
		// }

		for (int i = 0; i < humans.size(); i++) {
			if (zombies.get(i).getId_room() == humans.get(0).getId_room()) {
				if (zombies.get(i).getAggressivity() > humans.get(0).getCombat_capacity()) {
					System.out.println(humans.get(0).getName() + " en prend plein la gueule!");
					humans.get(0).getDamage(40);
				}
				if (zombies.get(i).getAggressivity() < humans.get(0).getCombat_capacity()) {
					System.out.println(humans.get(0).getName() + " défonce le zombie!");
					zombies.get(i).getDamage(60);
				}

			} else {
				System.out.println("Pas de zombie avec " + humans.get(0).getName());
			}
		}

		System.out.println("Vie des humains:");
		for (int i = 0; i < humans.size(); i++) {
			System.out.println("\t" + humans.get(i).getLife());
		}

		System.out.println("Vie des zombies:");
		for (int i = 0; i < zombies.size(); i++) {
			System.out.println("\t" + zombies.get(i).getLife());
		}
	}
}
