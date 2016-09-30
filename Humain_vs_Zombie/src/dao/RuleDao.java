package dao;

import java.util.List;

import model.Human;
import model.Item;
import model.Room;
import model.Zombie;

public interface RuleDao {
	
	public boolean masterEndCondition(List<Human> humans, List<Zombie> zombies);

	public Boolean endCondition(Human human, List<Zombie> zombies);

	public void fight(Human human, Zombie zombie, Room room, Item item);

	public Boolean checkAliveHuman(Human human);

	public Boolean checkAliveZombie(Zombie zombie);

	public void setItemCombatPoints(Human human, Item item);

	public void checkOrigin(Human human);

//	public void moveZombieToAliveHumanRoom(List<Human> humans, Zombie zombie);
	
	public void moveZombieToAliveHumanRoom(Human human, Zombie zombie);

	public void removeTheDead(List<Human> humans, Human human, List<Zombie> zombies, Zombie zombie);

	public boolean humanIsAlone(Human human, List<Zombie> zombies);

	public boolean zombieIsAlone(Zombie zombie, List<Human> humans);
}
