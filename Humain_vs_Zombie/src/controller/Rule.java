package controller;

import java.util.List;

import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class Rule {

	private List<Human> humans;
	private List<Item> items;
	private List<Room> rooms;
	private List<Zombie> zombies;

	public Rule(List<Human> humans, List<Item> items, List<Room> rooms, List<Zombie> zombies) {
		this.humans = humans;
		this.items = items;
		this.rooms = rooms;
		this.zombies = zombies;

		combat();
	}

	public Boolean endCondition(Boolean humanAlive, Boolean zombieAlive) {
		if (!humanAlive || !zombieAlive) {
			return false;
		} else {
			return true;
		}

	}

	public void combat() {

		for (int j = 0; j < humans.size(); j++) {
			for (int k = 0; k < zombies.size(); k++) {

				if (humans.get(j).getId_room() == zombies.get(k).getId_room()) {

					// Boucle pour déterminer les vainqueurs
					while (endCondition(humans.get(j).isAlive(), zombies.get(k).isAlive())) {

						// Verifier qui est mort
						if (!humans.get(j).isAlive()) {
							System.out.println(humans.get(j).getName() + " est mort! :(\n");
							humans.remove(j);
						}

						if (!zombies.get(k).isAlive()) {
							System.out.println(zombies.get(k).getName() + " est mort! :)\n");
							zombies.remove(k);
						}

						double powerRatio = (double) zombies.get(k).getAggressivity()
								/ (double) humans.get(j).getCombat_capacity();

						if (powerRatio < 0.5) {
							System.out
									.println(humans.get(j).getName() + " défonce le zombie " + zombies.get(k).getName()
											+ " à coup de " + Manager.getInstance()
													.getItemByRoom(items, humans.get(j).getId_room()).getName()
											+ "! (powerRatio: " + powerRatio + ")");
							zombies.get(k)
									.getDamage(powerRatio
											* 100 * Manager.getInstance()
													.getItemByRoom(items, humans.get(j).getId_room()).getCombat_points()
											/ 100);

						}

						if (powerRatio >= 0.5 && powerRatio <= 1) {
							System.out.println(
									humans.get(j).getName() + " rivalise beaucoup le zombie " + zombies.get(k).getName()
											+ " à coup de " + Manager.getInstance()
													.getItemByRoom(items, humans.get(j).getId_room()).getName()
											+ "! (powerRatio: " + powerRatio + ")");
							zombies.get(k).getDamage(powerRatio * 100 / 2);
							humans.get(j).getDamage(powerRatio * 100 / 3);
						}

						if (powerRatio >= 1 && powerRatio <= 1.5) {
							System.out.println(
									humans.get(j).getName() + " rivalise un peu le zombie " + zombies.get(k).getName()
											+ " à coup de " + Manager.getInstance()
													.getItemByRoom(items, humans.get(j).getId_room()).getName()
											+ "! (powerRatio: " + powerRatio + ")");
							zombies.get(k).getDamage(powerRatio * 100 / 3);
							humans.get(j).getDamage(powerRatio * 100 / 2);
						}
						if (powerRatio > 1.5) {
							System.out.println(humans.get(j).getName() + " se fait défoncer par le zombie "
									+ zombies.get(k).getName() + " à coup de "
									+ Manager.getInstance().getItemByRoom(items, humans.get(j).getId_room()).getName()
									+ "! (powerRatio: " + powerRatio + ")");
							humans.get(j).getDamage(powerRatio * 100);
						}
					}
				}
			}
		}
	}
}
