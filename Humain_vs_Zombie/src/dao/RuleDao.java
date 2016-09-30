package dao;

import java.util.List;

import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public interface RuleDao {

	public void fight(Human human, Zombie zombie, Room room, Item item);

	public Boolean endCondition(Human human, List<Zombie> zombies);

	public Boolean checkAliveHuman(Human human);

	public Boolean checkAliveZombie(Zombie zombie);

	public void setItemCombatPoints(Human human, Item item);

	public void checkOrigin(Human human);

	public void moveZombie(Zombie zombie, Integer rooms);

}
