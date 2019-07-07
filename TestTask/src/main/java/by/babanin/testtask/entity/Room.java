package by.babanin.testtask.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.StringJoiner;

@Document
public class Room {
    @Id
    private String id;
    private String number;
    private Integer amountOfBeds;
    private Double price;
    private Map<String, Period> reservation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getAmountOfBeds() {
        return amountOfBeds;
    }

    public void setAmountOfBeds(Integer amountOfBeds) {
        this.amountOfBeds = amountOfBeds;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<String, Period> getReservation() {
        return reservation;
    }

    public void setReservation(Map<String, Period> reservation) {
        this.reservation = reservation;
    }

    public boolean isReservation() {
        return reservation != null && !reservation.isEmpty();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Room.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("number='" + number + "'")
                .add("amountOfBeds=" + amountOfBeds)
                .add("price=" + price)
                .add("reservation=" + reservation)
                .toString();
    }
}
