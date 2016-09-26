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

	}

	public Boolean endCondition(Boolean humanAlive, Boolean zombieAlive) {
		if (!humanAlive || !zombieAlive) {
			return false;
		} else {
			return true;
		}

	}

	public void fight(Integer j, Integer k) {

		// Calcul du ratio de combat en fonction de la taille et l'aggressivité
		// des personnes
		double powerRatio = ((double) zombies.get(k).getAggressivity() + (double) zombies.get(k).getSize())
				/ ((double) humans.get(j).getCombat_capacity() + (double) humans.get(j).getSize());

		// Calcul des points d'environnements en fonction de la taille et
		// luminosité de la pièce
		double environmentInfluence = (double) Manager.getInstance().getRoomById(rooms, humans.get(j).getId_room())
				.getLight() + (double) Manager.getInstance().getRoomById(rooms, humans.get(j).getId_room()).getSize();

		// Calcul des points de damages
		double humanDamage = powerRatio
				* Manager.getInstance().getItemByRoomId(items, humans.get(j).getId_room()).getCombat_points();
		double zombieDamage = powerRatio * 100;

		if (powerRatio < 0.5) {
			zombieDamage = zombieDamage / 4;
			System.out.println(humans.get(j).getName() + " défonce le zombie " + zombies.get(k).getName()
					+ " à coup de " + Manager.getInstance().getItemByRoomId(items, humans.get(j).getId_room()).getName()
					+ "! (powerRatio: " + powerRatio + ", humanDamage: " + humanDamage + ")");
			zombies.get(k).getDamage(humanDamage);
			humans.get(j).getDamage(zombieDamage);
		}

		if (powerRatio >= 0.5 && powerRatio <= 1) {
			humanDamage = humanDamage / 2;
			zombieDamage = zombieDamage / 3;
			System.out.println(humans.get(j).getName() + " rivalise beaucoup le zombie " + zombies.get(k).getName()
					+ " à coup de " + Manager.getInstance().getItemByRoomId(items, humans.get(j).getId_room()).getName()
					+ "! (powerRatio: " + powerRatio + ", humanDamage: " + humanDamage + ")");
			zombies.get(k).getDamage(humanDamage);
			humans.get(j).getDamage(zombieDamage);
		}

		if (powerRatio >= 1 && powerRatio <= 1.5) {
			humanDamage = humanDamage / 3;
			zombieDamage = zombieDamage / 2;
			System.out.println(humans.get(j).getName() + " rivalise un peu le zombie " + zombies.get(k).getName()
					+ " à coup de " + Manager.getInstance().getItemByRoomId(items, humans.get(j).getId_room()).getName()
					+ "! (powerRatio: " + powerRatio + ", humanDamage: " + humanDamage + ")");
			zombies.get(k).getDamage(humanDamage);
			humans.get(j).getDamage(zombieDamage);
		}
		if (powerRatio > 1.5) {
			humanDamage = humanDamage / 4;
			System.out.println(humans.get(j).getName() + " se fait défoncer par le zombie " + zombies.get(k).getName()
					+ " à coup de " + Manager.getInstance().getItemByRoomId(items, humans.get(j).getId_room()).getName()
					+ "! (powerRatio: " + powerRatio + ", humanDamage: " + humanDamage + ")");
			zombies.get(k).getDamage(humanDamage);
			humans.get(j).getDamage(zombieDamage);
		}
	}

	public void setItemCombatPoints(Integer j, Integer k) {
		Item item = Manager.getInstance().getItemByRoomId(items, humans.get(j).getId_room());
		item.setCombat_points(item.getCombat_points() - (100 - item.getResistance()) / 2);
	}

	public void checkDeath(Integer j, Integer k) {
		// Verifier qui est mort
		if (!humans.get(j).isAlive()) {
			System.out.println("L'humain " + humans.get(j).getName() + " est mort! :(\n");
			humans.remove(j);
		}

		if (!zombies.get(k).isAlive()) {
			System.out.println("Le zombie " + zombies.get(k).getName() + " est mort! :)\n");
			zombies.remove(k);
		}
	}
}
