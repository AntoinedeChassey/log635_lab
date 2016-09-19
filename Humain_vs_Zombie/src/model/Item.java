package model;

public class Item {

	private Integer id_item;
	private Integer id_room;
	private String name;
	private double combat_points;
	private double resistance;

	public Item(Integer id_item, Integer id_room, String name, double combat_points, double resistance) {
		super();
		this.id_item = id_item;
		this.id_room = id_room;
		this.name = name;
		this.combat_points = combat_points;
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

	public double getCombat_points() {
		return combat_points;
	}

	public void setCombat_points(double combat_points) {
		this.combat_points = combat_points;
	}

	public double getResistance() {
		return resistance;
	}

	public void setResistance(double resistance) {
		this.resistance = resistance;
	}

}
