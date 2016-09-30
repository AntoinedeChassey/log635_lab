package controller;

import java.util.List;

import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class Main_BACKUP {

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

		showHumans(humans);
		showZombies(zombies);
		showHumansByRoomsWithZombies(humans, zombies);

		System.out.println("Lancement du scénario...\n\n");

		// Iteratation des règles
		for (int j = 0; j < humans.size(); j++) {
			// Check origins to set bonus or malus to human
			rules.checkOrigin(humans.get(j));

			for (int k = 0; k < zombies.size(); k++) {
				System.out.println(zombies.get(k).getId_room());

				// Variables
				Human human = humans.get(j);
				Zombie zombie = zombies.get(k);
				List<Zombie> zombiesInHumanRoom = world.getZombiesInHumanRoom(zombies, human);

				if (human.getId_room() == zombie.getId_room()) {

					/*
					 * 
					 * A DEFINIR
					 * 
					 */
					// Bouger le zombie dans une autre pièce si l'humain est
					// mort
					// if (rules.checkAliveHuman(human) == false) {
					// System.out.println("MOVE");
					// do {
					// rules.moveZombie(zombie, rooms.size());
					// } while (humans.get(j + 1).getId_room() !=
					// zombie.getId_room());
					// }

					// Boucle pour déterminer la fin du scénario

					while (!rules.endCondition(human, zombiesInHumanRoom)) {
						// Variables
						Room room = world.getRoomById(rooms, human.getId_room());
						Item item = world.getItemByRoomId(items, human.getId_room());

						System.out.println("L'humain " + human.getName() + " se bat avec " + zombie.getName());
						// Combat
						rules.fight(human, zombie, room, item);

						// Set new weapon combat points
						rules.setItemCombatPoints(human, item);

						// Remove the dead
						if (!rules.checkAliveHuman(human)) {
							System.out.println("\tL'humain " + human.getName() + " est mort! :(\n");
							// humans.remove(human);
						}
						if (!rules.checkAliveZombie(zombie)) {
							System.out.println("\tLe zombie " + zombie.getName() + " est mort! :)\n");
							// zombies.remove(zombie);
						}
						compteurIterationsFight++;
					}
				} else {
					rules.moveZombieToAliveHumanRoom(humans, zombie);
					compteurIterationsMove++;
				}
			}

		}

		showHumans(humans);
		showZombies(zombies);
		showHumansByRoomsWithZombies(humans, zombies);

		System.out.println("Nombre d'itérations de fight: " + compteurIterationsFight);
		System.out.println("Nombre d'itérations de move: " + compteurIterationsMove);

	}

	private static void showHumans(List<Human> humans) {
		System.out.println("\n\nVie des humains existants:");
		for (int i = 0; i < humans.size(); i++) {
			System.out.println("\t" + humans.get(i).getName() + ": " + humans.get(i).getLife());
		}
	}

	private static void showZombies(List<Zombie> zombies) {
		System.out.println("Vie des zombies existants:");
		for (int i = 0; i < zombies.size(); i++) {
			System.out.println("\t" + zombies.get(i).getName() + ": " + zombies.get(i).getLife());
		}
	}

	private static void showHumansByRoomsWithZombies(List<Human> humans, List<Zombie> zombies) {
		for (int j = 0; j < humans.size(); j++) {
			for (int k = 0; k < zombies.size(); k++) {
				if (humans.get(j).getId_room() == zombies.get(k).getId_room()) {
					System.out.println(humans.get(j).getName() + " est dans la pièce " + humans.get(j).getId_room()
							+ " avec le Z: " + zombies.get(k).getName());
				}
			}
		}
	}

	private static void launch(Human human, List<Zombie> zombiesInHumanRoom) {
		while (!rules.endCondition(human, zombiesInHumanRoom)) {
			for (int i = 0; i < zombiesInHumanRoom.size(); i++) {
				Zombie zombie = zombiesInHumanRoom.get(i);
				// Variables
				Room room = world.getRoomById(rooms, human.getId_room());
				Item item = world.getItemByRoomId(items, human.getId_room());

				System.out.println("L'humain " + human.getName() + " se bat avec " + zombie.getName());
				// Combat
				rules.fight(human, zombie, room, item);

				// Set new weapon combat points
				rules.setItemCombatPoints(human, item);

				compteurIterationsFight++;
			}
		}
	}
}
