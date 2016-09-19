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

	public void combat() {
		for (int i = 0; i < humans.size(); i++) {

			for (int j = 0; j < zombies.size(); j++) {

				if (zombies.get(j).getId_room() == humans.get(i).getId_room()) {

					double powerRatio = (double) zombies.get(j).getAggressivity()
							/ (double) humans.get(i).getCombat_capacity();

					if (powerRatio < 0.5) {
						System.out.println(
								humans.get(i).getName() + " défonce le zombie! (powerRatio: " + powerRatio + ")");
						zombies.get(j).getDamage(powerRatio * 100);
					}

					if (powerRatio >= 0.5 && powerRatio <= 1) {
						System.out.println(humans.get(i).getName() + " rivalise beaucoup le zombie! (powerRatio: "
								+ powerRatio + ")");
						zombies.get(j).getDamage(powerRatio * 100 / 2);
						humans.get(i).getDamage(powerRatio * 100 / 3);
					}

					if (powerRatio >= 1 && powerRatio <= 1.5) {
						System.out.println(humans.get(i).getName() + " rivalise un peu le zombie! (powerRatio: "
								+ powerRatio + ")");
						zombies.get(j).getDamage(powerRatio * 100 / 3);
						humans.get(i).getDamage(powerRatio * 100 / 2);
					}
					if (powerRatio > 1.5) {
						System.out.println(humans.get(i).getName() + " se fait défoncer par le zombie! (powerRatio: "
								+ powerRatio + ")");
						humans.get(i).getDamage(powerRatio * 100);
					}
				}
			}
		}
	}

}
