package dao.impl;

import java.util.Random;

import dao.RuleDao;
import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class RuleDaoImpl implements RuleDao {

	@Override
	public void fight(Human human, Zombie zombie, Room room, Item item) {
		// Calcul du ratio de combat en fonction de la taille et l'aggressivité
		// des personnes
		double powerRatio = ((double) zombie.getAggressivity() + (double) zombie.getSize())
				/ ((double) human.getCombat_capacity() + (double) human.getSize());

		// Calcul des points d'environnements (fonction de la taille et
		// luminosité de la pièce
		double environmentInfluence = (double) room.getLight() + (double) room.getSize();
		// System.out.println("Environnement: " + environmentInfluence);

		// Calcul des points de damages (fonction de l'aggressivité, de la
		// taille, de la capacité de combat et des points de combats des objets)
		double humanDamage = powerRatio * item.getDamage();
		double zombieDamage = powerRatio * 10;

		if (powerRatio < 0.5) {
			// Nouveaux faits (zombieDamage)
			zombieDamage = zombieDamage / 4;
			System.out.println(human.getName() + " défonce le zombie " + zombie.getName() + " à coup de "
					+ item.getName() + "! (powerRatio: " + powerRatio + ", humanDamage: " + humanDamage
					+ ", zombieDamage: " + zombieDamage + ")");
			zombie.getDamage(humanDamage);
			human.getDamage(zombieDamage);
		}

		else if (powerRatio >= 0.5 && powerRatio <= 1) {
			// Nouveaux faits (humanDamage et zombieDamage)
			humanDamage = humanDamage / 2;
			zombieDamage = zombieDamage / 3;
			System.out.println(human.getName() + " rivalise beaucoup le zombie " + zombie.getName() + " à coup de "
					+ item.getName() + "! (powerRatio: " + powerRatio + ", humanDamage: " + humanDamage
					+ ", zombieDamage: " + zombieDamage + ")");
			zombie.getDamage(humanDamage);
			human.getDamage(zombieDamage);
		}

		else if (powerRatio >= 1 && powerRatio <= 1.5) {
			// Nouveaux faits (humanDamage et zombieDamage)
			humanDamage = humanDamage / 3;
			zombieDamage = zombieDamage / 2;
			System.out.println(human.getName() + " rivalise un peu le zombie " + zombie.getName() + " à coup de "
					+ item.getName() + "! (powerRatio: " + powerRatio + ", humanDamage: " + humanDamage
					+ ", zombieDamage: " + zombieDamage + ")");
			zombie.getDamage(humanDamage);
			human.getDamage(zombieDamage);
		} else if (powerRatio > 1.5) {
			// Nouveaux faits (humanDamage)
			humanDamage = humanDamage / 4;
			System.out.println(human.getName() + " se fait défoncer par le zombie " + zombie.getName() + " à coup de "
					+ item.getName() + "! (powerRatio: " + powerRatio + ", humanDamage: " + humanDamage
					+ ", zombieDamage: " + zombieDamage + ")");
			zombie.getDamage(humanDamage);
			human.getDamage(zombieDamage);
		}

		else {
			System.out.println("Bug");
		}
	}

	@Override
	public Boolean endCondition(Human human, Zombie zombie) {
		if (!human.isAlive() || !zombie.isAlive()) {
			return false;
		} else {
			return true;
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
		if (item.getDamage() - (100 - item.getResistance()) / 10 >= 0) {
			item.setDamage(item.getDamage() - (100 - item.getResistance()) / 10);
		} else {
			item.setDamage(0);
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

	@Override
	public void moveZombie(Zombie zombie, Integer rooms) {
		Random r = new Random();
		int min = 1;
		int max = rooms;
		int result = r.nextInt(max-min) + min;
		zombie.setId_room(result);
	}
}
