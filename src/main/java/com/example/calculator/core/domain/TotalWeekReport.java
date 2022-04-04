package com.example.calculator.core.domain;

import java.time.LocalDateTime;

public class TotalWeekReport {
    private final  String technicianId ;
    private final Integer weekNumber;
    private final Integer totalHours;

    public TotalWeekReport(String technicianId, Integer weekNumber, Integer totalHours) {
        this.technicianId = technicianId;
        this.weekNumber = weekNumber;
        this.totalHours = totalHours;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public Integer getTotalHours() {
        return totalHours;
    }


}
