package co.com.ias.handyman.infranstructure.adapters.output;

import co.com.ias.handyman.infranstructure.logSystem.Log;
import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import co.com.ias.handyman.serviceTechnician.application.ports.output.ServiceTechnicianRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

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
}
