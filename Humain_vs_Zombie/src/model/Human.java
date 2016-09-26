package model;

public class Human {

	private Integer id_human;
	private Integer id_room;
	private String name;
	private double life;
	private double age;
	private double size;
	private double weight;
	private double combat_capacity;
	private String job;

	public Human(Integer id_human, Integer id_room, String name, double life, double age, double size, double weight,
			double combat_capacity, String job) {
		super();
		this.id_human = id_human;
		this.id_room = id_room;
		this.name = name;
		this.life = life;
		this.age = age;
		this.size = size;
		this.weight = weight;
		this.combat_capacity = combat_capacity;
		this.job = job;
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

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCombat_capacity() {
		return combat_capacity;
	}

	public void setCombat_capacity(double combat_capacity) {
		this.combat_capacity = combat_capacity;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
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
