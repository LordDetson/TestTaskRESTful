package by.babanin.testtask.controller;
import by.babanin.testtask.entity.*;
import by.babanin.testtask.service.holder.ValidationHandler;
import by.babanin.testtask.service.impl.RoomServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class HotelController {
    private final RoomServiceImpl roomService;

    public HotelController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/workschedule")
    public WorkSchedule getWorkSchedule() {
        return roomService.getWorkSchedule();
    }

    @PostMapping("/workschedule")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public WorkSchedule setWorkSchedule(@RequestBody WorkSchedule workSchedule) {
        ValidationHandler.validationPeriod(workSchedule.getWorkSchedule());
        ValidationHandler.lockInstallationWorkSchedule(roomService.getWorkSchedule());
        return roomService.setWorkSchedule(workSchedule);
    }

    @PostMapping("/room")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public Room createRoom(@RequestBody Room room) {
        ValidationHandler.checkRoomNumber(room);
        ValidationHandler.checkRoomPrice(room);
        ValidationHandler.roomNumberIsExist(roomService.getAllRooms(), room.getNumber());
        return roomService.addRoom(room);
    }

    @PutMapping("/room/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public Room updateRoom(
            @PathVariable("id") Room roomFromDb,
            @RequestBody Room room
    ) {
        ValidationHandler.checkRoomNumber(room);
        ValidationHandler.checkRoomPrice(room);
        if (!roomFromDb.getNumber().equals(room.getNumber()))
            ValidationHandler.roomNumberIsExist(roomService.getAllRooms(), room.getNumber());
        return roomService.updateRoom(roomFromDb, room);
    }

    @GetMapping("/rooms")
    public List<Room> getFreeRooms(@RequestBody Period period) {
        ValidationHandler.isNullWorkSchedule(roomService.getWorkSchedule());
        ValidationHandler.validationPeriod(period);
        ValidationHandler.checkOutOfPeriod(roomService.getWorkSchedule(), period);
        return roomService.getFreeRooms(period);
    }

    @PostMapping("/room/bookingFee")
    public BookingFee bookingFee(
            @RequestParam String number,
            @RequestParam String start,
            @RequestParam String finish
    ) {
        Room room = roomService.getRoomByNumber(number);
        Period period = convertToPeriod(start, finish);
        ValidationHandler.isNullWorkSchedule(getWorkSchedule());
        ValidationHandler.isRoomReservation(room);
        ValidationHandler.checkOutOfPeriod(getWorkSchedule(), period);
        return roomService.bookingFee(room, period);
    }

    @PostMapping("/room/reservation")
    public Room reservation(
            @AuthenticationPrincipal User user,
            @RequestParam String number,
            @RequestParam Double amount,
            @RequestParam String start,
            @RequestParam String finish
    ) {
        Room room = roomService.getRoomByNumber(number);
        Period period = convertToPeriod(start, finish);
        ValidationHandler.isNullWorkSchedule(getWorkSchedule());
        ValidationHandler.isRoomReservation(room);
        ValidationHandler.checkOutOfPeriod(getWorkSchedule(), period);
        ValidationHandler.validationAmount(roomService.bookingFee(room, period), amount);
        return roomService.reservation(user, roomService.getRoomByNumber(number), convertToPeriod(start, finish));
    }

    private Period convertToPeriod(@RequestParam String start, @RequestParam String finish) {
        Period period = new Period();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        period.setStart(LocalDateTime.from(dateTimeFormatter.parse(start)));
        period.setFinish(LocalDateTime.from(dateTimeFormatter.parse(finish)));
        return period;
    }
}
