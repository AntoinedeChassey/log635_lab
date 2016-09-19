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

		for (int i = 0; i < humans.size(); i++) {
				if (zombies.get(i).getId_room() == humans.get(0).getId_room()) {
				if (zombies.get(i).getAggressivity() > humans.get(0).getCombat_capacity()) {
					System.out.println(humans.get(0).getName() + " en prend plein la gueule!");
					humans.get(0).getDamage(40);
				}
				if (zombies.get(i).getAggressivity() < humans.get(0).getCombat_capacity()) {
					System.out.println(humans.get(0).getName() + " défonce le zombie!");
					zombies.get(i).getDamage(60);
				}
			}
		}

	}

}
