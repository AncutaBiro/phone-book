package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.AgendaService;
import org.fasttrackit.transfer.CreateAgendaRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet ("/agenda")
public class AgendaServlet extends HttpServlet {

    private AgendaService agendaService = new AgendaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreateAgendaRequest request = new ObjectMapper().readValue(req.getReader(), CreateAgendaRequest.class);

        try {
            agendaService.createAgenda(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was a error while processing your request" + e.getMessage());
        }

    }


}
