package com.example.calculator.infraestructure.controller.model;

import com.example.calculator.core.domain.TotalWeekReport;

public class TotalWeekReportDTO {
    private String technicianId;
    private Integer weekNumber;
    private Integer totalHours;
    private Integer hoursNight;
    private Integer hoursDay;
    private Integer hoursSunday;

    public TotalWeekReportDTO(String technicianId, Integer weekNumber, Integer totalHours, Integer hoursNight, Integer hoursDay, Integer hoursSunday) {
        this.technicianId = technicianId;
        this.weekNumber = weekNumber;
        this.totalHours = totalHours;
        this.hoursNight = hoursNight;
        this.hoursDay = hoursDay;
        this.hoursSunday = hoursSunday;
    }

    public TotalWeekReportDTO() {
    }

    public static TotalWeekReportDTO fromDomain(TotalWeekReport totalWeekReport) {
        return new TotalWeekReportDTO(
                totalWeekReport.getTechnicianId(),
                totalWeekReport.getWeekNumber(),
                totalWeekReport.getTotalHours(),
                totalWeekReport.getHoursNight(),
                totalWeekReport.getHoursDay(),
                totalWeekReport.getSundayHours()
        );
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getHoursNight() { return hoursNight; }

    public void setHoursNight(Integer hours_night) { this.hoursNight = hours_night; }

    public Integer getHoursDay() { return hoursDay; }

    public void setHoursDay(Integer hours_day) { this.hoursDay = hours_day; }

    public Integer getHoursSunday() { return hoursSunday; }

    public void setHoursSunday(Integer hours_sunday) { this.hoursSunday = hours_sunday;}
}
