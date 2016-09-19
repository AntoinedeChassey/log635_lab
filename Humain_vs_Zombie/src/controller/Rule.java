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

	public Boolean endCondition(Integer id_human, Integer id_zombie) {
		if ((humans.get(id_human).isAlive() || zombies.get(id_zombie).isAlive())
				|| (!humans.get(id_human).isAlive() && zombies.get(id_zombie).isAlive())
				|| (humans.get(id_human).isAlive() && !zombies.get(id_zombie).isAlive())) {
			return true;
		} else {
			return false;
		}

	}

	public Item getItemByRoom(Integer id_room) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId_room() == id_room) {
				return items.get(i);
			}
		}
		return null;
	}

	public void combat() {

		for (int i = 0; i < rooms.size(); i++) {

			for (int j = 0; j < humans.size(); j++) {

				for (int k = 0; k < zombies.size(); k++) {
					if (humans.get(j).getId_room() == zombies.get(k).getId_room()) {
						// while (endCondition(j, k)) {
						double powerRatio = (double) zombies.get(k).getAggressivity()
								/ (double) humans.get(j).getCombat_capacity();

						if (powerRatio < 0.5) {
							System.out
									.println(humans.get(j).getName() + " défonce le zombie " + zombies.get(k).getName()
											+ " à coup de " + this.getItemByRoom(humans.get(j).getId_room()).getName()
											+ "! (powerRatio: " + powerRatio + ")");
							zombies.get(k).setLife(powerRatio * 100
									* this.getItemByRoom(humans.get(k).getId_room()).getCombat_points() / 100);

						}

						if (powerRatio >= 0.5 && powerRatio <= 1) {
							System.out.println(
									humans.get(j).getName() + " rivalise beaucoup le zombie " + zombies.get(k).getName()
											+ " à coup de " + this.getItemByRoom(humans.get(j).getId_room()).getName()
											+ "! (powerRatio: " + powerRatio + ")");
							zombies.get(k).setLife(powerRatio * 100 / 2);
							humans.get(j).setLife(powerRatio * 100 / 3);
						}

						if (powerRatio >= 1 && powerRatio <= 1.5) {
							System.out.println(
									humans.get(i).getName() + " rivalise un peu le zombie " + zombies.get(k).getName()
											+ " à coup de " + this.getItemByRoom(humans.get(j).getId_room()).getName()
											+ "! (powerRatio: " + powerRatio + ")");
							zombies.get(k).setLife(powerRatio * 100 / 3);
							humans.get(j).setLife(powerRatio * 100 / 2);
						}
						if (powerRatio > 1.5) {
							System.out.println(humans.get(j).getName() + " se fait défoncer par le zombie "
									+ zombies.get(k).getName() + " à coup de "
									+ this.getItemByRoom(humans.get(j).getId_room()).getName() + "! (powerRatio: "
									+ powerRatio + ")");
							humans.get(j).setLife(powerRatio * 100);
						}

					}
				}
			}
		}
	}
	// }
}

// for (int i = 0; i < humans.size(); i++) {
//
// for (int j = 0; j < zombies.size(); j++) {
//
// while (humans.get(i).getLife() > 0 || zombies.get(j).getLife() > 0) {
// if (zombies.get(j).getId_room() == humans.get(i).getId_room()) {
//
// double powerRatio = (double) zombies.get(j).getAggressivity()
// / (double) humans.get(i).getCombat_capacity();
//
// if (powerRatio < 0.5) {
// System.out.println(humans.get(i).getName() + " défonce le zombie à coup de "
// + this.getItemByRoom(humans.get(i).getId_room()).getName() + "! (powerRatio:
// "
// + powerRatio + ")");
// zombies.get(j).getDamage(powerRatio * 100
// * this.getItemByRoom(humans.get(i).getId_room()).getCombat_points() / 100);
//
// }
//
// if (powerRatio >= 0.5 && powerRatio <= 1) {
// System.out.println(humans.get(i).getName() + " rivalise beaucoup le zombie à
// coup de "
// + this.getItemByRoom(humans.get(i).getId_room()).getName() + "! (powerRatio:
// "
// + powerRatio + ")");
// zombies.get(j).getDamage(powerRatio * 100 / 2);
// humans.get(i).getDamage(powerRatio * 100 / 3);
// }
//
// if (powerRatio >= 1 && powerRatio <= 1.5) {
// System.out.println(humans.get(i).getName() + " rivalise un peu le zombie à
// coup de "
// + this.getItemByRoom(humans.get(i).getId_room()).getName() + "! (powerRatio:
// "
// + powerRatio + ")");
// zombies.get(j).getDamage(powerRatio * 100 / 3);
// humans.get(i).getDamage(powerRatio * 100 / 2);
// }
// if (powerRatio > 1.5) {
// System.out.println(humans.get(i).getName() + " se fait défoncer par le zombie
// à coup de "
// + this.getItemByRoom(humans.get(i).getId_room()).getName() + "! (powerRatio:
// "
// + powerRatio + ")");
// humans.get(i).getDamage(powerRatio * 100);
// }
// }
// }
// }
// }
// }
