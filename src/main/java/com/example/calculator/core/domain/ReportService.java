package com.example.calculator.core.domain;



import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;

public class ReportService {
    private final  String technicianId ;
    private final String serviceId;
    private  final LocalDateTime startService;
    private  final LocalDateTime endService;
    private final Integer weekNumber;
    private final Integer totalHours;

    public ReportService(String technicianId, String serviceId, LocalDateTime startService, LocalDateTime endService, Integer week, Integer totalHours) {
        this.technicianId = Validate.notNull(technicianId);
        this.serviceId = Validate.notNull(serviceId);
        this.startService = Validate.notNull(startService);
        Validate.isTrue(LocalDateTime.now().isAfter(startService));
        this.endService = Validate.notNull(endService);
        Validate.isTrue(LocalDateTime.now().isAfter(startService) && endService.isBefore(LocalDateTime.now()));
        this.weekNumber = Validate.notNull(week);
                Validate.isTrue(week <= 53);
        this.totalHours = Validate.notNull(totalHours);

    }



    public String getTechnicianId() {
        return technicianId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public LocalDateTime getStartService() {
        return startService;
    }

    public LocalDateTime getEndService() {
        return endService;
    }

    public Integer getWeekNumber(){

        return weekNumber;
    }

    public Integer getTotalHours(){
        return totalHours;
    }
}
