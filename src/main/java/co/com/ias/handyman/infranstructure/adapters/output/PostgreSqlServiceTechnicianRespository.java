package co.com.ias.handyman.infranstructure.adapters.output;

import co.com.ias.handyman.infranstructure.logSystem.Log;
import co.com.ias.handyman.infranstructure.models.ServiceTechnicianDAO;
import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianFinalDate;
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

        } catch (SQLException exception) {
            Log logger = new Log(exception.getMessage(), exception.getSQLState());
            logger.logger();
            throw new RuntimeException("Error querying database", exception);
        }

    }

    @Override
    public Optional<ServiceTechnician> get(ServiceTechnicianStartDate startDate, ServiceTechnicianFinalDate finalDate) {
        String sql = "SELECT * FROM service_technician WHERE start_date = ? AND final_date = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, startDate.getValue());
            preparedStatement.setObject(2, finalDate.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ServiceTechnicianDAO serviceTechnicianDAO = ServiceTechnicianDAO.fromResultSet(resultSet);
                ServiceTechnician serviceTechnician = serviceTechnicianDAO.toDomain();
                return Optional.of(serviceTechnician);
            } else {
                return Optional.empty();
            }

        } catch (SQLException exception) {

            throw new RuntimeException("Error querying database", exception);
        }
    }
}
