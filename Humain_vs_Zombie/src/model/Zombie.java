package model;

public class Zombie {

	private Integer id_zombie;
	private Integer id_room;
	private String name;
	private Integer life;
	private Integer size;
	private Integer aggressivity;

	public Zombie(Integer id_zombie, Integer id_room, String name, Integer life, Integer size, Integer aggressivity) {
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

	public Integer getLife() {
		return life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getAggressivity() {
		return aggressivity;
	}

	public void setAggressivity(Integer aggressivity) {
		this.aggressivity = aggressivity;
	}

	// custom methods

	public void getDamage(Integer damage) {
		this.life = life - damage;
	}
}
