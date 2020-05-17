package org.fasttrackit.persistence;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {

    public void createAgenda (CreateAgendaRequest request) throws IOException, SQLException {

        String sql = "INSERT INTO agenda (first_name, last_name, phone_number) VALUES (?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }
    }

    public void updateAgenda(long id, UpdateAgendaRequest request) throws IOException, SQLException {

        String sql = "UPDATE agenda SET first_name = ? WHERE first_name = ? ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getFirstName());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteAgenda (long id) throws SQLException, IOException {

        String sql = "DELETE FROM agenda WHERE id = ? ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }

    }

    public List<Agenda> getAgenda () throws IOException, SQLException {

        String sql = "SELECT id, first_name, last_name, phone_number FROM agenda";

        List<Agenda> agenda = new ArrayList<>();

        try (Connection connection = DatabaseConfiguration.getConnection();
        Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Agenda contacts = new Agenda();
                contacts.setId(resultSet.getLong("id"));
                contacts.setFirstName(resultSet.getString("first_name"));
                contacts.setLastName(resultSet.getString("last_name"));
                contacts.setPhoneNumber(resultSet.getString("phone_number"));

                agenda.add(contacts);
            }
        }
      return agenda;
    }

    public List<Agenda> getAgendaByLastName (UpdateAgendaRequest lastName) throws IOException, SQLException {

        List<Agenda> agenda = new ArrayList<>();

        String sql = "SELECT id, first_name, last_name, phone_number FROM agenda WHERE last_name = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, lastName.getLastName());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Agenda contact = new Agenda();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));

                agenda.add(contact);
            }
        }
        return agenda;
    }

}



