package com.example.calculator.core.gateway;

import com.example.calculator.core.domain.ReportDates;
import com.example.calculator.core.domain.ReportService;

import java.util.List;

public interface ReportServiceReporsitory {


    //no nos retorna nada este método, porque ya esta guardado a no ser de que encarguemos al Db generar el id

    void insert(ReportService reportService);


    List<ReportDates> getListReport(String technicianId, Integer weekNumber);






}
