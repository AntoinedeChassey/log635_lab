package controller;

import java.util.List;

import dao.RuleDao;
import dao.impl.RuleDaoImpl;
import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public class RulesManager {

	private static RulesManager instance;

	public static RulesManager getInstance() {
		if (instance == null) {
			instance = new RulesManager();
		}
		return instance;
	}

	private RuleDao ruleDao = new RuleDaoImpl();

	private RulesManager() {
	}

	// Rule methods

	public void fight(Human human, Zombie zombie, Room room, Item item) {
		ruleDao.fight(human, zombie, room, item);
	}

	public Boolean endCondition(Human human, List<Zombie> zombies) {
		return ruleDao.endCondition(human, zombies);
	}

	public Boolean checkAliveHuman(Human human) {
		return ruleDao.checkAliveHuman(human);
	}

	public Boolean checkAliveZombie(Zombie zombie) {
		return ruleDao.checkAliveZombie(zombie);
	}

	public void setItemCombatPoints(Human human, Item roomItem) {
		ruleDao.setItemCombatPoints(human, roomItem);
	}

	public void checkOrigin(Human human) {
		ruleDao.checkOrigin(human);
	}

	public void moveZombie(Zombie zombie, Integer rooms) {
		ruleDao.moveZombie(zombie, rooms);
	}
}
