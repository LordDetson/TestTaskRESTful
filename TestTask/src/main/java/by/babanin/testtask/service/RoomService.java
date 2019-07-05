package by.babanin.testtask.service;

import by.babanin.testtask.dao.RoomRepository;
import by.babanin.testtask.dao.WorkScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final WorkScheduleRepository workScheduleRepository;

    public RoomService(RoomRepository roomRepository, WorkScheduleRepository workScheduleRepository) {
        this.roomRepository = roomRepository;
        this.workScheduleRepository = workScheduleRepository;
    }


}
