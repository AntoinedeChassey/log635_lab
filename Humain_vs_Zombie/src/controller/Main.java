package controller;

import java.util.ArrayList;
import java.util.List;

import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class Main {

	private static Integer compteurIterationsFight = 0;
	private static Integer compteurIterationsMove = 0;

	// Création du manager des faits
	private static FactsManager world = FactsManager.getInstance();
	// Création du manager des règles
	private static RulesManager rules = RulesManager.getInstance();

	// Création des faits
	private static List<Human> humans = world.getAllHumans();
	private static List<Item> items = world.getAllItems();
	private static List<Room> rooms = world.getAllRooms();
	private static List<Zombie> zombies = world.getAllZombies();

	public static void main(String[] args) {

		showHumans();
		showZombies();
		showHumansByRooms();
		showZombiesByRooms();

		System.out.println("Lancement du scénario...\n\n");

		// Iteratation des règles
		for (int j = 0; j < humans.size(); j++) {
			// Check origins to set bonus or malus to human
			rules.checkOrigin(humans.get(j));

			for (int k = 0; k < zombies.size(); k++) {
				if (thereAreHumans()) {
					// Variables
					Human human = humans.get(j);
					Zombie zombie = zombies.get(k);
					List<Zombie> zombiesInHumanRoom = world.getZombiesInHumanRoom(zombies, human);

					// Si l'humain est dans la pièce du zombie
					if (human.getId_room() == zombie.getId_room()) {

						launch(human, zombiesInHumanRoom);
						System.out.println("Vie humain:" + human.getLife());
						System.out.println("Vie zombie:" + zombie.getLife());
						rules.removeTheDead(humans, human, zombies, zombie);

					} else {
						// Sinon,
						// Si le zombie est vivant et QU'IL N'A PAS DE PIECE
						// AVEC UN HUMAIN, alors il va dans une pièce
						// ou un humain est vivant
						// if (zombie.isAlive() &&
						// world.zombieHasNoHumansToEat(zombies, humans)) {

						rules.moveZombieToAliveHumanRoom(humans, zombie);
						compteurIterationsMove++;
						List<Zombie> newZombiesInHumanRoom = new ArrayList<>();
						newZombiesInHumanRoom.add(zombie);
						// relaunch with human and zombies in same room
						launch(human, newZombiesInHumanRoom);
						rules.removeTheDead(humans, human, zombies, zombie);
					}
				}
			}
		}
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * TODO
		 * FAIRE PLUSIEURS FOIS LA DEMARCHE CI-DESSUS
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		showHumans();
		showZombies();
		showHumansByRooms();
		showZombiesByRooms();

		System.out.println("Nombre d'itérations de fight: " + compteurIterationsFight);
		System.out.println("Nombre d'itérations de move: " + compteurIterationsMove);

	}

	private static void showHumans() {
		System.out.println("\n\nVie des humains existants:");
		for (int i = 0; i < humans.size(); i++) {
			System.out.println("\t" + humans.get(i).getName() + ": " + humans.get(i).getLife());
		}
	}

	private static void showZombies() {
		System.out.println("Vie des zombies existants:");
		for (int i = 0; i < zombies.size(); i++) {
			System.out.println("\t" + zombies.get(i).getName() + ": " + zombies.get(i).getLife());
		}
	}

	private static void showHumansByRooms() {
		System.out.println("Humains dans pièces:");
		for (int i = 0; i < humans.size(); i++) {
			System.out.println("\t" + humans.get(i).getName() + " est dans la pièce " + humans.get(i).getId_room());
		}
	}

	private static void showZombiesByRooms() {
		System.out.println("Zombies dans pièces:");
		for (int i = 0; i < zombies.size(); i++) {
			System.out.println("\t" + zombies.get(i).getName() + " est dans la pièce " + zombies.get(i).getId_room());
		}
	}

	private static void launch(Human humanInTheRoom, List<Zombie> zombiesInHumanRoom) {
		while (rules.endCondition(humanInTheRoom, zombiesInHumanRoom)) {
			for (int i = 0; i < zombiesInHumanRoom.size(); i++) {
				Zombie zombie = zombiesInHumanRoom.get(i);
				// Variables
				Room room = world.getRoomById(rooms, humanInTheRoom.getId_room());
				Item item = world.getItemByRoomId(items, humanInTheRoom.getId_room());

				System.out.println("L'humain " + humanInTheRoom.getName() + " se bat avec " + zombie.getName());
				// Combat
				rules.fight(humanInTheRoom, zombie, room, item);

				// Set new weapon combat points
				rules.setItemCombatPoints(humanInTheRoom, item);

				compteurIterationsFight++;

				System.out.println("Vie humain: " + humanInTheRoom.getLife());
				System.out.println("Vie zombie: " + zombie.getLife());
			}
		}
	}

	private static Boolean thereAreHumans() {
		if (!humans.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
