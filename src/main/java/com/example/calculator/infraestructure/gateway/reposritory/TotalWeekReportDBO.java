package com.example.calculator.infraestructure.gateway.reposritory;

import com.example.calculator.core.domain.ReportService;
import com.example.calculator.core.domain.TotalWeekReport;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TotalWeekReportDBO {

    private String technicianId;
    private Integer weekNumber;
    private Integer totalHours;
    private Integer hoursNight;
    private Integer hoursDay;
    private Integer hoursSunday;

    public TotalWeekReportDBO(String technicianId, Integer weekNumber, Integer totalHours, Integer hoursDay, Integer hoursNight, Integer hoursSunday) {
        this.technicianId = technicianId;
        this.weekNumber = weekNumber;
        this.totalHours = totalHours;
        this.hoursDay = hoursDay;
        this.hoursNight = hoursNight;
        this.hoursSunday = hoursSunday;
    }

    public TotalWeekReportDBO() {
    }

    public TotalWeekReport toDomain() {
        return new TotalWeekReport(
                technicianId,
                weekNumber,
                totalHours,
                hoursDay,
                hoursNight,
                hoursSunday

        );
    }

    public static TotalWeekReportDBO fromResultSet(ResultSet resultSet) throws SQLException {
        TotalWeekReportDBO dbo1 = new TotalWeekReportDBO();

        dbo1.setTotalHours(resultSet.getInt("total_hours"));



        return dbo1;
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
