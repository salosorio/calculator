package com.example.calculator.infraestructure.controller.model;

import com.example.calculator.core.domain.ReportService;

import java.time.LocalDateTime;

//esto es un objeto de transferencia  de datos entre capas
public class ReportServiceDTO {
    private String serviceId;
    private String id;
    private LocalDateTime startService;
    private LocalDateTime endService;
    private Integer weekNumber;
    private Integer totalHours;

    public ReportServiceDTO(String serviceId, String id, LocalDateTime startService, LocalDateTime endService, Integer weekNumber, Integer totalHours) {
        this.serviceId = serviceId;
        this.id = id;
        this.startService = startService;
        this.endService = endService;
        this.weekNumber = weekNumber;
        this.totalHours = totalHours;
    }

    public ReportServiceDTO() {
    }

    public static ReportServiceDTO fromDomain(ReportService reportService) {
        return new ReportServiceDTO(
                reportService.getServiceId(),
                reportService.getTechnicianId(),
                reportService.getStartService(),
                reportService.getEndService(),
                reportService.getWeekNumber(),
                reportService.getTotalHours()
        );
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getWeekNumber() { return weekNumber; }

    public void setWeekNumber(Integer weekNumber) { this.weekNumber = weekNumber; }

    public Integer getTotalHours() { return totalHours; }

    public void setTotalHours(Integer totalHours) { this.totalHours = totalHours; }
}
