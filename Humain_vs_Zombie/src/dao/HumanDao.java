package dao;

import java.util.List;

import model.Human;

public interface HumanDao {

	public List<Human> getAllHumans();

	public Human getHumanByName(List<Human> humans, String name);
}
