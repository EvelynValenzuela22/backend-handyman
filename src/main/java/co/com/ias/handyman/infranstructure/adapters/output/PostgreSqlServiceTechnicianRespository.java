package co.com.ias.handyman.infranstructure.adapters.output;

import co.com.ias.handyman.infranstructure.logSystem.Log;
import co.com.ias.handyman.infranstructure.models.ServiceTechnicianDAO;
import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianFinalDate;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianIdService;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianIdTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianStartDate;
import co.com.ias.handyman.serviceTechnician.application.ports.output.ServiceTechnicianRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class PostgreSqlServiceTechnicianRespository  implements ServiceTechnicianRepository {

    private final DataSource dataSource;

    public PostgreSqlServiceTechnicianRespository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(ServiceTechnician serviceTechnician) {
        String sql = "INSERT INTO service_technician VALUES(?,?,?,?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, serviceTechnician.getIdService().getValue());
            preparedStatement.setLong(2, serviceTechnician.getIdTechnician().getValue());
            preparedStatement.setObject(3, serviceTechnician.getStartDate().getValue());
            preparedStatement.setObject(4, serviceTechnician.getFinalDate().getValue());

            preparedStatement.execute();

        } catch (SQLException | RuntimeException exception) {
            Log logger = new Log(exception.getMessage(), exception.getStackTrace().toString());
            logger.logger();
            throw new RuntimeException("Error querying database", exception);
        }

    }

    @Override
    public Optional<ServiceTechnician> getServiceBetweenDates(ServiceTechnicianStartDate startDate, ServiceTechnicianFinalDate finalDate, ServiceTechnicianIdService idService) {
        String sql = "SELECT * FROM service_technician WHERE service_id = ? AND (start_date BETWEEN ? AND ? OR final_date BETWEEN ? AND ?) ";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, idService.getValue());
            preparedStatement.setObject(2, startDate.getValue());
            preparedStatement.setObject(3, finalDate.getValue());
            preparedStatement.setObject(4, startDate.getValue());
            preparedStatement.setObject(5, finalDate.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ServiceTechnicianDAO serviceTechnicianDAO = ServiceTechnicianDAO.fromResultSet(resultSet);
                ServiceTechnician serviceTechnician = serviceTechnicianDAO.toDomain();
                return Optional.of(serviceTechnician);
            } else {
                return Optional.empty();
            }

        } catch (SQLException | RuntimeException exception) {

            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public Optional<ServiceTechnician> getTechnicianBetweenDates(ServiceTechnicianStartDate startDate, ServiceTechnicianFinalDate finalDate, ServiceTechnicianIdTechnician idTechnician) {
        String sql = "SELECT * FROM service_technician WHERE technician_id = ?  AND (start_date BETWEEN ? AND ? OR final_date BETWEEN ? AND ?) ";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, idTechnician.getValue());
            preparedStatement.setObject(2, startDate.getValue());
            preparedStatement.setObject(3, finalDate.getValue());
            preparedStatement.setObject(4, startDate.getValue());
            preparedStatement.setObject(5, finalDate.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ServiceTechnicianDAO serviceTechnicianDAO = ServiceTechnicianDAO.fromResultSet(resultSet);
                ServiceTechnician serviceTechnician = serviceTechnicianDAO.toDomain();
                return Optional.of(serviceTechnician);
            } else {
                return Optional.empty();
            }

        } catch (SQLException | RuntimeException exception) {

            throw new RuntimeException("Error querying database", exception);
        }
    }
}
