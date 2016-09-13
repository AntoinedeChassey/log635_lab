package model;

public class Room {
	
	private Integer id_room;
	private String name;
	private Integer size;
	private Integer light;
	
	public Room(Integer id_room, String name, Integer size, Integer light) {
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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getLight() {
		return light;
	}

	public void setLight(Integer light) {
		this.light = light;
	}
	
	
}
