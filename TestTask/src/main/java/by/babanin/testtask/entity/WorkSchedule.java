package by.babanin.testtask.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WorkSchedule {
    @Id
    private String id;
    private Period workSchedule;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Period getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(Period workSchedule) {
        this.workSchedule = workSchedule;
    }
}
