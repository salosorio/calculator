package com.example.calculator.infraestructure.service;

import com.example.calculator.core.domain.ReportDates;
import com.example.calculator.core.domain.TotalWeekReport;
import com.example.calculator.core.gateway.ReportServiceReporsitory;
import com.example.calculator.infraestructure.controller.model.ReportServiceInput;
import com.example.calculator.core.domain.ReportService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class ReportServiceService {

    //constantes
    private static final LocalTime startRegularHours = LocalTime.of(7,0);
    private static final LocalTime endRegularHours = LocalTime.of(19,59);

    //usamos esta interfaz por practicidad e independencia de modulos NOTA=  usar las interfaces
    private final ReportServiceReporsitory reportServiceReporsitory;

    public ReportServiceService(ReportServiceReporsitory reportServiceReporsitory) {
        this.reportServiceReporsitory = reportServiceReporsitory;
    }

    public ReportService createReport(ReportServiceInput reportServiceInput) {
        int week = calculateWeekNumber(reportServiceInput.getStartService());
        int hours = Math.toIntExact(calculateHours(reportServiceInput.getStartService(), reportServiceInput.getEndService()));

        try {  //para hacer pruebas (suplir unos argumentos y sepamos la respuesta pero el id no sé que retorna)
            ReportService report = new ReportService(
                    reportServiceInput.getTechnicianId(),
                    reportServiceInput.getServiceId(),
                    reportServiceInput.getStartService(),
                    reportServiceInput.getEndService(),
                    week,
                    hours
            );
            reportServiceReporsitory.insert(report); //aca almacenamos el servicio

            return report;
            //acá capturamos por qué podria fallar una validación de datos
        } catch (NullPointerException | IllegalArgumentException e) { //error de validacion de datos //el notNull nos lanza Null pointer exception -is true nos lanza Illegal Argument
            throw new IllegalArgumentException("error validating service data", e);                                            //lanzamos una nueva excepción
        }
    }

    //procesar la lista de reportes y sumar las horas totales, extras, etc
    private TotalWeekReport processReports(String technicianId, Integer weekNumber) {
        TotalWeekReport totalWeekReport = new TotalWeekReport(technicianId,weekNumber,0,0,0,0);
        List<ReportDates> reportsList = reportServiceReporsitory.getListReport(technicianId,weekNumber);


        for(ReportDates reportDates : reportsList){ //recorro la lista de los reportes

            //reportService es el objeto que tiene cada reporte que cumple con las condiciones
            //technicianId y weekNumber
            //primero calculo el valor de las horas de un reporte
            LocalDateTime start = reportDates.getStartService();
            LocalDateTime end = reportDates.getEndService();
            int totalHours = Math.toIntExact(calculateHours(start, end));
            //sumo este valor a el total previamente calculado
            totalHours = totalWeekReport.getTotalHours() + totalHours ;
            int sundayHours = Math.toIntExact(calculateHoursInSunday(start, end));
            sundayHours = totalWeekReport.getSundayHours() + sundayHours;
            int nightHours = Math.toIntExact(calculateNightHours(start,end));
            nightHours = totalWeekReport.getHoursNight() + nightHours;
            //seteo ese valor al objeto totalWeekReport
            totalWeekReport.setTotalHours(totalHours);
            totalWeekReport.setSundayHours(sundayHours);
            totalWeekReport.setHoursNight(nightHours);
        }
        return totalWeekReport;
    }

    //Metodo para calcular numero de la semana

    public Integer calculateWeekNumber(LocalDateTime localDateTime){

        LocalDate localDate =  localDateTime.toLocalDate();
        TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        Integer weekNumber = localDate.get(weekOfYear);
        return weekNumber;
    }
    //Metodo para calcular horas
    private Long calculateHours(LocalDateTime startService, LocalDateTime endService){
        Long hours =  ChronoUnit.HOURS.between(startService,endService);
        return  hours;
    }

    private int calculateHours(LocalTime startServiceTime, LocalTime endServiceTime){
        return Math.toIntExact(ChronoUnit.HOURS.between(startServiceTime,endServiceTime));
    }

    //método para sunday
    private Long calculateHoursInSunday(LocalDateTime startService, LocalDateTime endService){
      long sundayHoursWorks = 0L;

      if(startService.getDayOfWeek() == DayOfWeek.SUNDAY){
         sundayHoursWorks = calculateHours(startService,endService);
      }
      return sundayHoursWorks;
    }

    //calcular si es horario nocturno
    private boolean isNightHours(LocalDateTime startService, LocalDateTime endService) {
        LocalTime startService1 = startService.toLocalTime();
        LocalTime endService1 = endService.toLocalTime();
        if (startService1.isAfter(endRegularHours) || endService1.isBefore(startRegularHours)
        || startService1.isBefore(startRegularHours) || endService1.isAfter(endRegularHours)){
            return true;
        }
        return false;
    }

    //intervalos
    private Long processNightHours(LocalDateTime startService, LocalDateTime endService){
        LocalTime startService1 = startService.toLocalTime();
        LocalTime endService1 = endService.toLocalTime();
        long nightHoursWorks = 0L;

        //intervalo antes de que empiecen las horas normales
        if(startService1.isBefore(startRegularHours) && endService1.isBefore(startRegularHours)){
              nightHoursWorks = calculateHours(startService,endService);

        //intervalo después de que termina las horas normales
        }else if(startService1.isAfter(endRegularHours) && endService1.isAfter(endRegularHours)){
            nightHoursWorks = calculateHours(startService,endService);

        //intervalo que empieza y termina durante las horas normales
        }else if(startService1.isAfter(startRegularHours) && endService1.isBefore(endRegularHours)){
            nightHoursWorks = 0;

        //intervalo que inicia antes de la hora normal  y termina antes de la hora normal
        }else if(startService1.isBefore(startRegularHours) && endService1.isAfter(startRegularHours) && endService1.isBefore(endRegularHours)){
            //sumar horas de start service a regular start
           nightHoursWorks = calculateHours(startService1, startRegularHours);

        //intervalo inicia antes de terminar horario normal y termina antes del iniciar el horario normal
        }else if(startService1.isBefore(endRegularHours)&& endService1.isAfter(endRegularHours) && endService1.isBefore(startRegularHours)){
            //sumar horas de end regular a end service
            nightHoursWorks = calculateHours(endRegularHours,endService1);

        //intervalo inicia antes del horario normal y finaliza despues del horario normal
        } else if(startService1.isBefore(startRegularHours) && endService1.isAfter(endRegularHours)){
            nightHoursWorks = calculateHours(startService1, startRegularHours) + calculateHours(endRegularHours, endService1);

            //sumar ambos cortes de horas nocturnas
        }
        return nightHoursWorks;
    }

    //calcular las horas nocturnas
    private Long calculateNightHours(LocalDateTime startService, LocalDateTime endService){
         boolean night = isNightHours(startService,endService);

        Long nightHoursWorks =0l;
        if(night){
            nightHoursWorks = processNightHours(startService,endService);
        }
        return nightHoursWorks;
    }

    public TotalWeekReport getTotalReport(String technicianId, Integer weekNumber) {
        return processReports(technicianId, weekNumber);
    }
}




