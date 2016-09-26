package controller;

import java.util.List;

import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class Main {

	private static Integer compteurIterations = 0;

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

		for (int j = 0; j < humans.size(); j++) {
			for (int k = 0; k < zombies.size(); k++) {
				if (humans.get(j).getId_room() == zombies.get(k).getId_room()) {
					System.out.println(humans.get(j).getName() + " est dans la pi�ce " + humans.get(j).getId_room()
							+ " avec le Z: " + zombies.get(k).getName());
				}
			}
		}

		System.out.println("Lancement du scénario...\n\n");

		// Création des règles
		Rule rules = new Rule(humans, items, rooms, zombies);

		// Iteratation des règles
		for (int j = 0; j < humans.size(); j++) {
			for (int k = 0; k < zombies.size(); k++) {
				if (humans.get(j).getId_room() == zombies.get(k).getId_room()) {

					// Boucle pour déterminer les vainqueurs
					while (rules.endCondition(humans.get(j).isAlive(), zombies.get(k).isAlive())) {

						compteurIterations++;

						// Combat
						rules.fight(j, k);

						// Set new weapon combat points
						rules.setItemCombatPoints(j, k);

						// Check who is alive
						rules.checkDeath(j, k);
					}
				}
			}
		}

		System.out.println("\n\nVie des humains à la fin:");
		for (int i = 0; i < humans.size(); i++) {
			System.out.println("\t" + humans.get(i).getName() + ": " + humans.get(i).getLife());
		}

		System.out.println("Vie des zombies à la fin:");
		for (int i = 0; i < zombies.size(); i++) {
			System.out.println("\t" + zombies.get(i).getName() + ": " + zombies.get(i).getLife());
		}

		System.out.println("Nombre d'itérations: " + compteurIterations);
	}
}
