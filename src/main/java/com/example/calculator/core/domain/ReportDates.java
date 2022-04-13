package com.example.calculator.core.domain;

import java.time.LocalDateTime;

public class ReportDates {
    private LocalDateTime startService;
    private LocalDateTime endService;

    public ReportDates(LocalDateTime startService, LocalDateTime endService) {
        this.startService = startService;
        this.endService = endService;
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
