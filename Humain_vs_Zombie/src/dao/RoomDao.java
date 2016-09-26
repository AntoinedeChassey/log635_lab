package dao;

import java.util.List;

import model.Room;

public interface RoomDao {

	public List<Room> getAllRooms();

	public Room getRoomById(List<Room> rooms, Integer id_room);

}
