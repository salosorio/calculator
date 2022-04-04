package com.example.calculator.infraestructure.service;

import com.example.calculator.core.domain.TotalWeekReport;
import com.example.calculator.core.gateway.ReportServiceReporsitory;
import com.example.calculator.infraestructure.controller.model.ReportServiceDTO;
import com.example.calculator.infraestructure.controller.model.ReportServiceInput;
import com.example.calculator.core.domain.ReportService;
import com.example.calculator.infraestructure.controller.model.TotalWeekReportDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportServiceService {

    //usamos esta interfaz ´por practicidad e independencia de modulos NOTA=  usar las interfaces
    private final ReportServiceReporsitory reportServiceReporsitory;

    public ReportServiceService(ReportServiceReporsitory reportServiceReporsitory) {
        this.reportServiceReporsitory = reportServiceReporsitory;
    }


    //Método para calcular numero de la semana

    public Integer calculateWeekNumber(LocalDateTime localDateTime){

        LocalDate localDate =  localDateTime.toLocalDate();
        TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        Integer weekNumber = localDate.get(weekOfYear);

        return weekNumber;
    }


        public Optional<TotalWeekReportDTO> getTotalWeekReport(String technicianId, Integer weekNumber){
            String technicianId1 = technicianId;
            Integer weekNumber1 = weekNumber;
            Optional<TotalWeekReport> optionalTotalWeekReport = reportServiceReporsitory.get(technicianId,weekNumber);
            return optionalTotalWeekReport//la clase Optional tiene un método .map que transforma los valores internos
                    .map(totalWeekReport -> { //lambda expression
                        return TotalWeekReportDTO.fromDomain(totalWeekReport);
                    });
        }



    public ReportService createReport(ReportServiceInput reportServiceInput) {
        int week = calculateWeekNumber(reportServiceInput.getStartService());
        int hrs = 0;
        try {  //para hacer pruebas (suplir unos argumentos y sepamos la respuesta pero el id no sé que retorna)
            ReportService report= new ReportService(
                    reportServiceInput.getTechnicianId(),
                    reportServiceInput.getServiceId(),
                    reportServiceInput.getStartService(),
                    reportServiceInput.getEndService(),
                    week,
                    hrs

            );
            reportServiceReporsitory.insert(report); //aca almacenamos el servicio

            return report;
            //acá capturamos por qué podria fallar una validación de datos
        } catch (NullPointerException | IllegalArgumentException e){ //error de validacion de datos //el notNull nos lanza Null pointer exception -is true nos lanza Illegal Argument
            throw new IllegalArgumentException("error validating service data", e);                                            //lanzamos una nueva excepción
        }
    }


}
