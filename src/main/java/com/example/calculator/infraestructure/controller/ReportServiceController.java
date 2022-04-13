package com.example.calculator.infraestructure.controller;

import com.example.calculator.core.domain.TotalWeekReport;
import com.example.calculator.infraestructure.controller.model.ReportServiceDTO;
import com.example.calculator.infraestructure.controller.model.ReportServiceInput;
import com.example.calculator.core.domain.ReportService;
import com.example.calculator.infraestructure.service.ReportServiceService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/report")  // Es la url que va estar disponible
public class ReportServiceController {

    private final ReportServiceService service;  //inyecta la clase service

    public ReportServiceController (ReportServiceService reportServiceService) {
        this.service = reportServiceService;
    }


    //Existe dos get (get one que es con id - get list )
    @GetMapping(value = "/{technician}/{week}")
    public TotalWeekReport getTotalWeekReport(
            @PathVariable("technician") String technicianId, @PathVariable("week") Integer weekNumber
    ) {
        TotalWeekReport totalWeekReport = service.getTotalReport(technicianId, weekNumber);
        return totalWeekReport;
    }

  /*  @GetMapping
    public List<ReportServiceDTO> listReportService() {
        return service.listReportService();
    }*/


    @PostMapping
        public ReportServiceDTO createReport(
                @RequestBody ReportServiceInput reportServiceInput) {
            //El controlador redirije al servicio de app l a operacion , se encarga de formatearla y la devuelve segun su capa
            ReportService reportService = service.createReport(reportServiceInput);
            return ReportServiceDTO.fromDomain(reportService);

        }


}
