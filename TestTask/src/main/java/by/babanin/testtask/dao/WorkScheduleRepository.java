package by.babanin.testtask.dao;

import by.babanin.testtask.entity.WorkSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkScheduleRepository extends MongoRepository<WorkSchedule, String> {
}
