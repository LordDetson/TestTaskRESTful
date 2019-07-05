package by.babanin.testtask.entity;

import java.time.LocalDateTime;

public class Period {
    private LocalDateTime start;
    private LocalDateTime finish;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }
}
