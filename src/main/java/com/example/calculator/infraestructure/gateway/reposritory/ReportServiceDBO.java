package com.example.calculator.infraestructure.gateway.reposritory;

import com.example.calculator.core.domain.ReportService;

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

    public ReportService toDomain() {
        return new ReportService(
                technicianId,
                 serviceId,
                startService,
                endService,
                weekNumber,
                totalHours
        );
    }

    //con el método fromResult asigno como parametros (Resultst) para crear la clase ReporDBO
    // para esto debo crear un objeto llamado dbo y asigno los valores que salen del DB
    public static ReportServiceDBO fromResultSet(ResultSet resultSet) throws SQLException {
        ReportServiceDBO dbo = new ReportServiceDBO();

        dbo.setTechnicianId (resultSet.getString("technician_id"));
        dbo.setServiceId(resultSet.getString("service_id"));
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
