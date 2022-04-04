package com.example.calculator.infraestructure.gateway.reposritory;

import com.example.calculator.core.domain.ReportService;
import com.example.calculator.core.domain.TotalWeekReport;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TotalWeekReportDBO {

    private String technicianId;
    private Integer weekNumber;
    private Integer totalHours;

    public TotalWeekReportDBO(String technicianId, Integer weekNumber, Integer totalHours) {
        this.technicianId = technicianId;
        this.weekNumber = weekNumber;
        this.totalHours = totalHours;
    }

    public TotalWeekReportDBO() {
    }

    public TotalWeekReport toDomain() {
        return new TotalWeekReport(
                technicianId,
                weekNumber,
                totalHours
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
