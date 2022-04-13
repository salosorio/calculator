package com.example.calculator.core.domain;

public class TotalWeekReport {
    private final  String technicianId ;
    private final Integer weekNumber;
    private Integer totalHours;
    private Integer hoursNight;
    private Integer hoursDay;
    private Integer sundayHours;

    public TotalWeekReport(String technicianId, Integer weekNumber, Integer totalHours, Integer hoursNight, Integer hoursDay, Integer sundayHours) {
        this.technicianId = technicianId;
        this.weekNumber = weekNumber;
        this.totalHours = totalHours;
        this.hoursNight = hoursNight;
        this.hoursDay = hoursDay;
        this.sundayHours = sundayHours;
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

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getHoursNight() {
        return hoursNight;
    }

    public void setHoursNight(Integer hoursNight) {
        this.hoursNight = hoursNight;
    }

    public Integer getHoursDay() {
        return hoursDay;
    }

    public void setHoursDay(Integer hoursDay) {
        this.hoursDay = hoursDay;
    }

    public Integer getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(Integer sundayHours) {
        this.sundayHours = sundayHours;
    }
}
