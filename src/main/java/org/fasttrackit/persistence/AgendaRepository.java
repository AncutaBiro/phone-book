package org.fasttrackit.persistence;

import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void updateAgenda (UpdateAgendaRequest request) throws IOException, SQLException {

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





    }



