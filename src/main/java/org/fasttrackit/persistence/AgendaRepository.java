package org.fasttrackit.persistence;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {

    public void createAgenda (CreateAgendaRequest request) throws IOException, SQLException, ClassNotFoundException {

        String sql = "INSERT INTO agenda (first_name, last_name, phone_number) VALUES (?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }
    }

    public void updateAgenda(long id, UpdateAgendaRequest request) throws IOException, SQLException, ClassNotFoundException {

        String sql = "UPDATE agenda SET first_name = ? WHERE id = ? ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        }

        //vreau sa setez parametrul de cautare lastName in loc de id.
//        String sql = "UPDATE agenda SET first_name = ? WHERE last_name = ? ";
//
//        try (Connection connection = DatabaseConfiguration.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setString(1, request1.getFirstName());
//            preparedStatement.setString(2, request2.getFirstName());
//
//            preparedStatement.executeUpdate();
//        }

    }

    public void deleteAgenda (long id) throws SQLException, IOException, ClassNotFoundException {

        String sql = "DELETE FROM agenda WHERE id = ? ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }

    }

    public List<Agenda> getAgenda () throws IOException, SQLException, ClassNotFoundException {

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

    public List<Agenda> getAgenda (UpdateAgendaRequest lastName) throws IOException, SQLException, ClassNotFoundException {

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



