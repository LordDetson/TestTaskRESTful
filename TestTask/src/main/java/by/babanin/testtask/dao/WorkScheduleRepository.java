package by.babanin.testtask.dao;

import by.babanin.testtask.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel> {
}
