package dao.impl;

import java.util.List;
import java.util.Random;

import dao.RuleDao;
import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class RuleDaoImpl implements RuleDao {

	@Override
	public boolean masterEndCondition(List<Human> humans, List<Zombie> zombies) {
		if (humans.isEmpty() || zombies.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	// @Override
	// public Boolean endCondition(Human humanInTheRoom, List<Zombie>
	// zombiesInHumanRoom) {
	// Boolean stop = true;
	// for (int i = 0; i < zombiesInHumanRoom.size(); i++) {
	// if (humanInTheRoom.isAlive() || zombiesInHumanRoom.get(i).isAlive()) {
	// stop = false;
	// } else {
	// stop = true;
	// break;
	// }
	// }
	// return stop;
	// }

	@Override
	public Boolean endCondition(Human humanInTheRoom, List<Zombie> zombiesInHumanRoom) {
		boolean[] zombiesAlive = new boolean[zombiesInHumanRoom.size()];

		// Si l'humain dans la pièce est en vie
		if (humanInTheRoom.isAlive()) {
			for (int i = 0; i < zombiesInHumanRoom.size(); i++) {
				if (zombiesInHumanRoom.get(i).isAlive()) {
					zombiesAlive[i] = true;
				} else {
					zombiesAlive[i] = false;
				}

			}
			// Si aucun zombie mort (aucun false dans la liste), return true
			for (boolean b : zombiesAlive)
				if (!b)
					return false;
			return true;
		}
		return false;
	}

	@Override
	public void fight(Human human, Zombie zombie, Room room, Item item) {

		// Calcul du ratio de combat en fonction de la taille et
		// l'aggressivité
		// des personnes
		double powerRatio = ((double) zombie.getAggressivity() + (double) zombie.getSize())
				/ ((double) human.getCombat_capacity() + (double) human.getSize());

		// Calcul des points d'environnements (fonction de la taille et
		// luminosité de la pièce
		double environmentInfluence = (double) room.getLight() + (double) room.getSize();

		// System.out.println("Environnement: " + environmentInfluence);

		// Calcul des points de damages (fonction de l'aggressivité, de la
		// taille, de la capacité de combat et des points de combats des
		// objets)
		double humanDamage = powerRatio * item.getDamage();
		double zombieDamage = powerRatio * 10;

		if (environmentInfluence > 50) {
			Random ran = new Random();
			double criticalShot = ran.nextInt(3);
			humanDamage = humanDamage + criticalShot;
			System.out.println(
					"Critical shot due to environment influence - " + human.getName() + " gets +" + criticalShot);
		}
		if (environmentInfluence > 75) {
			Random ran = new Random();
			double criticalShot = ran.nextInt(7);
			humanDamage = humanDamage + criticalShot;
			System.out.println(
					"Critical shot due to environment influence - " + human.getName() + " gets +" + criticalShot);
		}

		if (powerRatio < 0.5) {
			// Nouveaux faits (zombieDamage)
			zombieDamage = zombieDamage / 4;
			// System.out.println(human.getName() + " défonce le zombie " +
			// zombie.getName() + " à coup de "
			// + item.getName() + "! (powerRatio: " + powerRatio + ",
			// humanDamage: " + humanDamage
			// + ", zombieDamage: " + zombieDamage + ")");
			zombie.getDamage(humanDamage);
			human.getDamage(zombieDamage);
		}

		else if (powerRatio >= 0.5 && powerRatio <= 1) {
			// Nouveaux faits (humanDamage et zombieDamage)
			humanDamage = humanDamage / 2;
			zombieDamage = zombieDamage / 3;
			// System.out.println(human.getName() + " rivalise beaucoup le
			// zombie " + zombie.getName() + " à coup de "
			// + item.getName() + "! (powerRatio: " + powerRatio + ",
			// humanDamage: " + humanDamage
			// + ", zombieDamage: " + zombieDamage + ")");
			zombie.getDamage(humanDamage);
			human.getDamage(zombieDamage);
		}

		else if (powerRatio >= 1 && powerRatio <= 1.5) {
			// Nouveaux faits (humanDamage et zombieDamage)
			humanDamage = humanDamage / 3;
			zombieDamage = zombieDamage / 2;
			// System.out.println(human.getName() + " rivalise un peu le zombie
			// " + zombie.getName() + " à coup de "
			// + item.getName() + "! (powerRatio: " + powerRatio + ",
			// humanDamage: " + humanDamage
			// + ", zombieDamage: " + zombieDamage + ")");
			zombie.getDamage(humanDamage);
			human.getDamage(zombieDamage);
		} else if (powerRatio > 1.5) {
			// Nouveaux faits (humanDamage)
			humanDamage = humanDamage / 4;
			// System.out.println(human.getName() + " se fait défoncer par le
			// zombie " + zombie.getName() + " à coup de "
			// + item.getName() + "! (powerRatio: " + powerRatio + ",
			// humanDamage: " + humanDamage
			// + ", zombieDamage: " + zombieDamage + ")");
			zombie.getDamage(humanDamage);
			human.getDamage(zombieDamage);
		}

		else {
			System.out.println("Bug");
		}
	}

	@Override
	public Boolean checkAliveHuman(Human human) {
		if (human.isAlive()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean checkAliveZombie(Zombie zombie) {
		if (zombie.isAlive()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void setItemCombatPoints(Human human, Item item) {
		if (item.getDamage() - (100 - item.getResistance()) / 10 > 1) {
			item.setDamage(item.getDamage() - (100 - item.getResistance()) / 10);
		} else {
			item.setDamage(1);
		}
	}

	@Override
	public void checkOrigin(Human human) {
		if (human.getJob() == "Fireman") {
			human.giveBonusLife(30);
		}
		if (human.getJob() == "Policeman") {
			human.giveBonusLife(50);
		}
		if (human.getJob() == "Military") {
			human.giveBonusLife(70);
		}
	}

	// @Override
	// public void moveZombieToAliveHumanRoom(List<Human> humans, Zombie zombie)
	// {
	// for (Human human : humans) {
	// if (human.isAlive()) {
	// System.out.println("[MOVE] Le zombie " + zombie.getName() + " va dans la
	// pièce de " + human.getName()
	// + " (" + human.getId_room() + ")");
	// zombie.setId_room(human.getId_room());
	// break;
	// }
	// }
	// }

	@Override
	public void moveZombieToAliveHumanRoom(Human human, Zombie zombie) {
		if (human.isAlive()) {
			System.out.println("[MOVE] Le zombie " + zombie.getName() + " va dans la pièce de " + human.getName() + " ("
					+ human.getId_room() + ")\n");
			zombie.setId_room(human.getId_room());
		}
	}

	@Override
	public void removeTheDead(List<Human> humans, Human human, List<Zombie> zombies, Zombie zombie) {
		if (!human.isAlive()) {
			System.out.println("\tL'humain " + human.getName() + " est mort! :(\n");
			humans.remove(human);
		}
		if (!zombie.isAlive()) {
			System.out.println("\tLe zombie " + zombie.getName() + " est mort! :)\n");
			zombies.remove(zombie);
		}
	}

	@Override
	public boolean humanIsAlone(Human human, List<Zombie> zombies) {
		for (Zombie zombie : zombies) {
			if (human.getId_room() == zombie.getId_room()) {
				return false;
			}
		}
		System.out.println("\t\tL'humain " + human.getName() + " est tout seul!\n");
		return true;
	}

	@Override
	public boolean zombieIsAlone(Zombie zombie, List<Human> humans) {
		for (Human human : humans) {
			if (zombie.getId_room() == human.getId_room()) {
				return false;
			}
		}
		System.out.println("\t\tLe zombie " + zombie.getName() + " est tout seul!\n");
		return true;
	}

}
