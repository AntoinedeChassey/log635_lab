package model;

public class Room {

	private Integer id_room;
	private String name;
	private double size;
	private double light;

	public Room(Integer id_room, String name, double size, double light) {
		super();
		this.id_room = id_room;
		this.name = name;
		this.size = size;
		this.light = light;
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

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getLight() {
		return light;
	}

	public void setLight(double light) {
		this.light = light;
	}

}
