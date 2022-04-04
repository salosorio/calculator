package com.example.calculator.infraestructure.gateway;

import com.example.calculator.core.domain.ReportService;
import com.example.calculator.core.domain.TotalWeekReport;
import com.example.calculator.core.gateway.ReportServiceReporsitory;
import com.example.calculator.infraestructure.gateway.reposritory.ReportServiceDBO;
import com.example.calculator.infraestructure.gateway.reposritory.TotalWeekReportDBO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Repository
public class SqlReportServiceRepository  implements ReportServiceReporsitory {

    private final DataSource dataSource;

    public SqlReportServiceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(ReportService reportService) {
        String sql = "INSERT into report_service (technician_id,service_id,start_service, end_service, week_number, total_hours) VALUES (?, ?, ?, ? , ?, ?)";
        try(Connection connection = dataSource.getConnection(); //las conexiones pueden fallar
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //Timestamp hace parte del paquete java.sql, por eso hay que convertir el LocalDateTime en TimeStamp para poder mandar datos al DB
            Timestamp startTimestamp = Timestamp.valueOf(reportService.getStartService());
            Timestamp endTimestamp = Timestamp.valueOf(reportService.getEndService());

            preparedStatement.setString(1,reportService.getTechnicianId());
            preparedStatement.setString(2,reportService.getServiceId());
            preparedStatement.setTimestamp(3, startTimestamp);
            preparedStatement.setTimestamp(4, endTimestamp);
            preparedStatement.setInt(5,reportService.getWeekNumber());
            preparedStatement.setInt(6,reportService.getTotalHours());


            preparedStatement.executeUpdate();
            //no hay qye hacer validación
        }
        catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }

    }

    @Override
   public Optional<TotalWeekReport> get(String technicianId, Integer weekNumber) {
        String sql = "SELECT total_hours FROM report_service WHERE technician_id = ? AND week_number = ? ";

        try (Connection connection = dataSource.getConnection(); //las conexiones pueden fallar
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //solo tengo q settear
            preparedStatement.setString(1, technicianId);
            preparedStatement.setInt(2,weekNumber);

            //El preparedStatement ejecuta el Query y retorna un resultSer
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                //el objeto que se creo apartir de resultSet lo almaceno en el objeto maintenanceDBO
                TotalWeekReportDBO totalWeekReportDBO = TotalWeekReportDBO.fromResultSet(resultSet);
                //el totalWeekRDbo lo va a convertir en un domain
                TotalWeekReport totalWeekReport = totalWeekReportDBO.toDomain();

                //se usa of al saber que no es nulo
                return Optional.of(totalWeekReport);
            } else {
                return Optional.empty(); //retorna u vacio porque no hay registro
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }

    }
}



