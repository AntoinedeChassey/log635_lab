package model;

public class Zombie {

	private Integer id_zombie;
	private Integer id_room;
	private String name;
	private double life;
	private double size;
	private double aggressivity;

	public Zombie(Integer id_zombie, Integer id_room, String name, double life, double size, double aggressivity) {
		super();
		this.id_zombie = id_zombie;
		this.id_room = id_room;
		this.name = name;
		this.life = life;
		this.size = size;
		this.aggressivity = aggressivity;
	}

	public Integer getId_zombie() {
		return id_zombie;
	}

	public void setId_zombie(Integer id_zombie) {
		this.id_zombie = id_zombie;
	}

	public Integer getId_room() {
		return id_room;
	}

	public void setId_room(Integer id_room) {
		this.id_room = id_room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getAggressivity() {
		return aggressivity;
	}

	public void setAggressivity(double aggressivity) {
		this.aggressivity = aggressivity;
	}

	// custom methods

	public void getDamage(double damage) {
		if (damage > 0) {
			this.setLife(this.getLife() - damage);
		}
	}

	public Boolean isAlive() {
		if (this.life > 0) {
			return true;
		} else {
			return false;
		}
	}
}
