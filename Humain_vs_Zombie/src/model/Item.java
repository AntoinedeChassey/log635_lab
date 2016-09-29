package model;

public class Item {

	private Integer id_item;
	private Integer id_room;
	private String name;
	private double damage;
	private double resistance;

	public Item(Integer id_item, Integer id_room, String name, double damage, double resistance) {
		super();
		this.id_item = id_item;
		this.id_room = id_room;
		this.name = name;
		this.damage = damage;
		this.resistance = resistance;
	}

	public Integer getId_item() {
		return id_item;
	}

	public void setId_item(Integer id_item) {
		this.id_item = id_item;
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

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getResistance() {
		return resistance;
	}

	public void setResistance(double resistance) {
		this.resistance = resistance;
	}

}
