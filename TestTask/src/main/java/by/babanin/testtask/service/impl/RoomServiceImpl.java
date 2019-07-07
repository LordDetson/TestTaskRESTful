package by.babanin.testtask.service.impl;

import by.babanin.testtask.dao.RoomRepository;
import by.babanin.testtask.dao.WorkScheduleRepository;
import by.babanin.testtask.entity.*;
import by.babanin.testtask.service.RoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final WorkScheduleRepository workScheduleRepository;

    public RoomServiceImpl(RoomRepository roomRepository, WorkScheduleRepository workScheduleRepository) {
        this.roomRepository = roomRepository;
        this.workScheduleRepository = workScheduleRepository;
    }

    @Override
    public WorkSchedule getWorkSchedule() {
        List<WorkSchedule> list = workScheduleRepository.findAll();
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public WorkSchedule setWorkSchedule(WorkSchedule workSchedule) {
        workScheduleRepository.deleteAll();
        return workScheduleRepository.save(workSchedule);
    }

    @Override
    public List<Room> getFreeRooms(Period period) {
        return roomRepository.findRoomsByReservationIsNull();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Room roomFromDb, Room room) {
        BeanUtils.copyProperties(room, roomFromDb, "id", "reservation");
        return roomRepository.save(roomFromDb);
    }

    @Override
    public Room getRoomByNumber(String number) {
        return roomRepository.findByNumber(number);
    }

    @Override
    public Room reservation(User user, Room room, Period period) {
        Map<String, Period> reservation = new HashMap<>();
        reservation.put(user.getId(), period);
        room.setReservation(reservation);
        return roomRepository.save(room);
    }

    @Override
    public BookingFee bookingFee(Room room, Period period) {
        BookingFee bookingFee = new BookingFee();
        long days = ChronoUnit.DAYS.between(period.getStart(), period.getFinish());
        bookingFee.setDays(days);
        bookingFee.setAmount(days * room.getPrice());
        return bookingFee;
    }
}
