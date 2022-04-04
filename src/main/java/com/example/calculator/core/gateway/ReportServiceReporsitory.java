package com.example.calculator.core.gateway;

import com.example.calculator.core.domain.ReportService;
import com.example.calculator.core.domain.TotalWeekReport;

import java.util.Optional;

public interface ReportServiceReporsitory {


    //no nos retorna nada este método, porque ya esta guardado a no ser de que encarguemos al Db generar el id

    void insert(ReportService reportService);

    Optional<TotalWeekReport> get(String technicianId, Integer weekNumber);
    //Optional es una clase que empaqueta
    //es para que me devuelva un valor opcional ya que puede devolver null u el objeto como tal
}
