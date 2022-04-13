package com.example.calculator.infraestructure.gateway.reposritory;

import com.example.calculator.core.domain.ReportDates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportServiceDBO {
    private String technicianId;
    private String serviceId;
    private LocalDateTime startService;
    private LocalDateTime endService;
    private Integer weekNumber;
    private Integer totalHours;

    public ReportServiceDBO(String technicianId,String serviceId,  LocalDateTime startService, LocalDateTime endService, Integer weekNumber, Integer totalHours) {
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.startService = startService;
        this.endService = endService;
        this.weekNumber = weekNumber;
        this.totalHours = totalHours;
    }

    public ReportServiceDBO() {
    }

    public ReportDates toReportDate() {
        return new ReportDates(
               startService,
                endService
        );
    }

    //con el método fromResult asigno como parametros (Resultst) para crear la clase ReporDBO
    // para esto debo crear un objeto llamado dbo y asigno los valores que salen del DB
    public static ReportServiceDBO fromResultSet(ResultSet resultSet) throws SQLException {
        ReportServiceDBO dbo = new ReportServiceDBO();


        dbo.setStartService(resultSet.getTimestamp("start_service").toLocalDateTime());
        dbo.setEndService(resultSet.getTimestamp("end_service").toLocalDateTime());

        return dbo;
    }



    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getStartService() {
        return startService;
    }

    public void setStartService(LocalDateTime startService) {
        this.startService = startService;
    }

    public LocalDateTime getEndService() {
        return endService;
    }

    public void setEndService(LocalDateTime endService) {
        this.endService = endService;
    }


}
