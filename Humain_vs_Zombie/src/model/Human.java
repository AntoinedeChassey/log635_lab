package model;

public class Human {

	private Integer id_human;
	private Integer id_room;
	private String name;
	private Integer life;
	private Integer age;
	private Integer size;
	private Integer weight;
	private Integer combat_capacity;

	public Human(Integer id_human, Integer id_room, String name, Integer life, Integer age, Integer size,
			Integer weight, Integer combat_capacity) {
		super();
		this.id_human = id_human;
		this.id_room = id_room;
		this.name = name;
		this.life = life;
		this.age = age;
		this.size = size;
		this.weight = weight;
		this.combat_capacity = combat_capacity;
	}

	public Integer getId_human() {
		return id_human;
	}

	public void setId_human(Integer id_human) {
		this.id_human = id_human;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getCombat_capacity() {
		return combat_capacity;
	}

	public void setCombat_capacity(Integer combat_capacity) {
		this.combat_capacity = combat_capacity;
	}

	
	// custom methods
	
	public void getDamage(Integer damage) {
		this.life = life - damage;
	}
}
