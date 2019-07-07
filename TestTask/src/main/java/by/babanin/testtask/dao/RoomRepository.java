package by.babanin.testtask.dao;

import by.babanin.testtask.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findRoomsByReservationIsNull();

    Room findByNumber(String number);
}
