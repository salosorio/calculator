package com.example.calculator.infraestructure.controller.model;


import java.time.LocalDateTime;

//lo que vamos a recibir en el mundo exterior
public class ReportServiceInput {
    private String serviceId;
    private String technicianId;
    private LocalDateTime startService;
    private LocalDateTime endService;

    public ReportServiceInput(String serviceId, String technicianId, LocalDateTime startService, LocalDateTime endService) {
        this.serviceId = serviceId;
        this.technicianId = technicianId;
        this.startService = startService;
        this.endService = endService;

    }

    public ReportServiceInput() {
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
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
