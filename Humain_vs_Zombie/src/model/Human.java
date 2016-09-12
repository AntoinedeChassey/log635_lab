package model;

public class Human {

	private Integer id_human;
	private String name;
	private Integer life;
	private Integer age;
	private Integer size;
	private Integer weight;
	private Integer combat_capacity;

	public Human(Integer id_human, String name, Integer life, Integer age, Integer size, Integer weight,
			Integer combat_capacity) {
		super();
		this.id_human = id_human;
		this.name = name;
		this.life = life;
		this.age = age;
		this.size = size;
		this.weight = weight;
		this.combat_capacity = combat_capacity;
	}

}
