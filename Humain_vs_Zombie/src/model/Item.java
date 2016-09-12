package model;

public class Item {
	
	private Integer id_item;
	private String name;
	private Integer combat_points;
	private Integer resistance;
	
	public Item(Integer id_item, String name, Integer combat_points, Integer resistance) {
		super();
		this.id_item = id_item;
		this.name = name;
		this.combat_points = combat_points;
		this.resistance = resistance;
	}
	
	
}
