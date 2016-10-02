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
	private static FactsManager facts = FactsManager.getInstance();
	// Création du manager des règles
	private static RulesManager rules = RulesManager.getInstance();

	// Création des faits
	private static List<Human> humans = facts.getAllHumans();
	private static List<Item> items = facts.getAllItems();
	private static List<Room> rooms = facts.getAllRooms();
	private static List<Zombie> zombies = facts.getAllZombies();

	public static void main(String[] args) {

		showHumans();
		showZombies();
		showHumansByRooms();
		showZombiesByRooms();

		System.out.println("Lancement du scénario...\n\n");

		// Condition maître, qui vérifie que soit des humains existent, soit des
		// zombies existent mais pas les deux
		while (rules.masterEndCondition(humans, zombies))
			main();

		showHumans();
		showZombies();
		showHumansByRooms();
		showZombiesByRooms();

		System.out.println("Nombre d'itérations de fight dans les pièces: " + compteurIterationsFight);
		System.out.println("Nombre d'itérations de move de zombies: " + compteurIterationsMove);

	}

	private static void showHumans() {
		System.out.println("\n\nVie des humains existants:");
		for (Human human : humans) {
			System.out.println("\t" + human.getName() + ": " + human.getLife());
		}
	}

	private static void showZombies() {
		System.out.println("Vie des zombies existants:");
		for (Zombie zombie : zombies) {
			System.out.println("\t" + zombie.getName() + ": " + zombie.getLife());
		}
	}

	private static void showHumansByRooms() {
		System.out.println("Humains dans pièces:");
		for (Human human : humans) {
			System.out.println("\t" + human.getName() + " est dans la pièce " + human.getId_room());
		}
	}

	private static void showZombiesByRooms() {
		System.out.println("Zombies dans pièces:");
		for (Zombie zombie : zombies) {
			System.out.println("\t" + zombie.getName() + " est dans la pièce " + zombie.getId_room());
		}
	}

	// Method that launches combat for human and zombies in the same room
	private static void launch(Human humanInTheRoom, List<Zombie> zombiesInHumanRoom) {
		while (rules.endCondition(humanInTheRoom, zombiesInHumanRoom)) {
			for (Zombie zombie : zombiesInHumanRoom) {
				// Variables
				Room room = facts.getRoomById(rooms, humanInTheRoom.getId_room());
				Item item = facts.getItemByRoomId(items, humanInTheRoom.getId_room());

				System.out.println("L'humain " + humanInTheRoom.getName() + " se bat avec " + zombie.getName());
				// Combat
				rules.fight(humanInTheRoom, zombie, room, item);

				// Set new weapon combat points
				rules.setItemCombatPoints(humanInTheRoom, item);

				compteurIterationsFight++;

				System.out.println("[STAT] Vie humain: " + humanInTheRoom.getLife());
				System.out.println("[STAT] Vie zombie: " + zombie.getLife());
			}
		}
	}

	private static void main() {
		// Iteratation des règles
		for (int j = 0; j < humans.size(); j++) {
			// Check origins to set bonus or malus to human
			rules.checkOrigin(humans.get(j));

			for (int k = 0; k < zombies.size(); k++) {
				if (rules.thereAreHumans(humans)) {
					// Variables
					Human human = humans.get(j);
					Zombie zombie = zombies.get(k);
					List<Zombie> zombiesInHumanRoom = facts.getZombiesInHumanRoom(zombies, human);

					// Si l'humain est dans la pièce du zombie
					if (human.getId_room() == zombie.getId_room()) {

						// Launch combat for human and zombies in the same room
						launch(human, zombiesInHumanRoom);
						rules.removeTheDead(humans, human, zombies, zombie);

					} else {
						// Sinon,
						// Si le zombie est vivant et QU'IL N'A PAS DE PIECE
						// AVEC UN HUMAIN, alors il va dans une pièce
						// ou un humain est vivant
						// if (zombie.isAlive() &&
						// facts.zombieHasNoHumansToEat(zombies, humans)) {
						/*
						 * Check if human doesn't already have zombies to fight
						 * with
						 */
						if (rules.humanIsAlone(human, zombies) && rules.zombieIsAlone(zombie, humans)) {
							rules.moveZombieToAliveHumanRoom(human, zombie);
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
		}
	}
}
