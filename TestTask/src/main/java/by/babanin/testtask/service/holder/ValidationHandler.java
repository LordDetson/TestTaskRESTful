package by.babanin.testtask.service.holder;

import by.babanin.testtask.entity.BookingFee;
import by.babanin.testtask.entity.Period;
import by.babanin.testtask.entity.Room;
import by.babanin.testtask.entity.WorkSchedule;
import by.babanin.testtask.service.exception.PeriodException;
import by.babanin.testtask.service.exception.PriceException;
import by.babanin.testtask.service.exception.RoomException;
import by.babanin.testtask.service.exception.WorkScheduleException;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationHandler {
    public static void checkOutOfPeriod(WorkSchedule workSchedule, Period period) {
        if (period.getStart().isBefore(workSchedule.getWorkSchedule().getStart()) ||
                period.getFinish().isAfter(workSchedule.getWorkSchedule().getFinish())) {
            throw new PeriodException("Период выходит за рамки графика работы гостиницы" +
                    "\nperiod=" + period +
                    "\nworkSchedule=" + workSchedule);
        }
    }

    public static void isNullWorkSchedule(WorkSchedule workSchedule) {
        if (workSchedule == null)
            throw new WorkScheduleException("Нельзя выполнить это действие, " +
                    "так как график работы гостиницы еще не установлен.");
    }

    public static void checkRoomNumber(Room room) {
        if (StringUtils.isEmpty(room.getNumber())) throw new RoomException("Отсудствует номер комнаты");
    }

    public static void checkRoomPrice(Room room) {
        if (room.getPrice() == null) throw new RoomException("Отсудствует цена на комнату");
    }

    public static void roomNumberIsExist(List<Room> allRoom, String number) {
        boolean roomNumberIsNotExist = allRoom.stream().map(Room::getNumber)
                .anyMatch(s -> s.equals(number));
        if (roomNumberIsNotExist) throw new RoomException("Комната с number " + number + " присудствует");
    }

    public static void isRoomReservation(Room room) {
        if (room.isReservation()) throw new RoomException("Комната с number " + room.getNumber() + " забронирована");
    }

    public static void validationAmount(BookingFee bookingFee, Double amount) {
        if (!amount.equals(bookingFee.getAmount())) {
            throw new PriceException("Не достаточно средств для того, чтобы забронировать комнату. " +
                    "Сумма к оплате должна составлять " + bookingFee.getAmount() + " за " + bookingFee.getDays() + " дней. " +
                    "Внесенная сумма составляет " + amount);
        }
    }

    public static void validationPeriod(Period period) {
        if (!period.getStart().isBefore(period.getFinish()))
            throw new WorkScheduleException("Не корректно установлоен период. " +
                    "period=" + period);
    }

    public static void lockInstallationWorkSchedule(WorkSchedule workSchedule) {
        if (workSchedule != null) {
            Period period = workSchedule.getWorkSchedule();
            if (period.getFinish().isAfter(LocalDateTime.now()))
                throw new WorkScheduleException("Установить время работы гостиницы можно только после " +
                        "истечения строка работы гостиницы");
        }
    }
}
