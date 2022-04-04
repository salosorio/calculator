package com.example.calculator.infraestructure.controller.model;

import com.example.calculator.core.domain.TotalWeekReport;

public class TotalWeekReportDTO {
    private String technicianId;
    private Integer weekNumber;
    private Integer totalHours;

    public TotalWeekReportDTO(String technicianId, Integer weekNumber, Integer totalHours) {
        this.technicianId = technicianId;
        this.weekNumber = weekNumber;
        this.totalHours = totalHours;
    }

    public TotalWeekReportDTO() {
    }

    public static TotalWeekReportDTO fromDomain(TotalWeekReport totalWeekReport) {
        return new TotalWeekReportDTO(
                totalWeekReport.getTechnicianId(),
                totalWeekReport.getWeekNumber(),
                totalWeekReport.getTotalHours()
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
}
