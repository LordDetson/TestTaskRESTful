package by.babanin.testtask.dao;

import by.babanin.testtask.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
}
