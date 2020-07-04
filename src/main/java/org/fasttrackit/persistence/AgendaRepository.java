package org.fasttrackit.persistence;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.DeleteAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {

    public void createAgenda(CreateAgendaRequest request) throws IOException, SQLException, ClassNotFoundException {

        String sql = "INSERT INTO agenda (first_name, last_name, phone_number, email, favourite) VALUES (?,?,?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhoneNumber());
            preparedStatement.setString(4, request.getEmail());
            preparedStatement.setBoolean(5, request.isFavourite());

            preparedStatement.executeUpdate();
        }
    }

    public void updateAgenda(long id, UpdateAgendaRequest request) throws IOException, SQLException, ClassNotFoundException {

        String sql = "UPDATE agenda SET first_name = ?, last_name = ?, phone_number = ?, email= ?, favourite = ? WHERE id = ? ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhoneNumber());
            preparedStatement.setString(4, request.getEmail());
            preparedStatement.setBoolean(5, request.isFavourite());
            preparedStatement.setLong(6, id);

            preparedStatement.executeUpdate();
        }
    }

//    public void updateAgenda(long id, UpdateAgendaRequest request) throws IOException, SQLException, ClassNotFoundException {
//
//        String sql = "UPDATE agenda SET favourite = ? WHERE id = ? ";
//
//        try (Connection connection = DatabaseConfiguration.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setBoolean(1, request.isFavourite());
//            preparedStatement.setLong(2, id);
//
//            preparedStatement.executeUpdate();
//        }
//    }

    public void deleteAgenda(Long id1) throws SQLException, IOException, ClassNotFoundException {

        String sql = "DELETE FROM agenda WHERE id = ? ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id1);

            preparedStatement.executeUpdate();
        }

    }

    public void deleteAgenda(DeleteAgendaRequest request) throws SQLException, IOException, ClassNotFoundException {

        String sql = "DELETE FROM agenda WHERE id IN (?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

//            preparedStatement.set(1, request);

            preparedStatement.executeUpdate();
        }

    }

    public List<Agenda> getAgenda() throws IOException, SQLException, ClassNotFoundException {

        String sql = "SELECT id, first_name, last_name, phone_number, email, favourite FROM agenda";

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
                contacts.setEmail(resultSet.getString("email"));
                contacts.setFavourite(resultSet.getBoolean("favourite"));

                agenda.add(contacts);
            }
        }
        return agenda;
    }

    public List<Agenda> getAgenda(Long id) throws IOException, SQLException, ClassNotFoundException {

        List<Agenda> agenda = new ArrayList<>();

        String sql = "SELECT id, first_name, last_name, phone_number, email, favourite FROM agenda WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Agenda contact = new Agenda();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));
                contact.setEmail(resultSet.getString("email"));
                contact.setFavourite(resultSet.getBoolean("favourite"));

                agenda.add(contact);
            }
        }
        return agenda;
    }

}



