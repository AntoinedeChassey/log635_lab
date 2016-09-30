package dao;

import java.util.List;

import model.Human;
import model.Zombie;

public interface ZombieDao {

	public List<Zombie> getAllZombies();

	public List<Zombie> getZombiesInHumanRoom(List<Zombie> zombies, Human human);

	public boolean zombieHasNoHumansToEat(List<Zombie> zombies, List<Human> humans);
	
}
