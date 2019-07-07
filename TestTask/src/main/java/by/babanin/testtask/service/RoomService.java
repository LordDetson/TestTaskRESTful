package by.babanin.testtask.service;

import by.babanin.testtask.entity.*;

import java.util.List;

public interface RoomService {
    WorkSchedule getWorkSchedule();

    WorkSchedule setWorkSchedule(WorkSchedule workSchedule);

    List<Room> getFreeRooms(Period period);

    List<Room> getAllRooms();

    Room addRoom(Room room);

    Room updateRoom(Room roomFromDb, Room room);

    Room getRoomByNumber(String number);

    Room reservation(User user, Room room, Period period);

    BookingFee bookingFee(Room room, Period period);
}
