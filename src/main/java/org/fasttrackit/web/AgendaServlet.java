package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.domain.Agenda;
import org.fasttrackit.service.AgendaService;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet ("/agenda")
public class AgendaServlet extends HttpServlet {

    private AgendaService agendaService = new AgendaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreateAgendaRequest request = ObjectMapperConfiguration.OBJECT_MAPPER
                .readValue(req.getReader(), CreateAgendaRequest.class);

        try {
            agendaService.createAgenda(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was a error while processing your request" + e.getMessage());
        }

    }

// daca vreau sa caut dupa lastName de ex in loc de id, cum as face???

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        UpdateAgendaRequest request = ObjectMapperConfiguration.OBJECT_MAPPER
                .readValue(req.getReader(), UpdateAgendaRequest.class);

        try {
            agendaService.updateAgenda(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was a error while processing your request" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        try {
            agendaService.deleteAgenda(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was a error while processing your request" + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String lastName = req.getParameter("lastName");
//        cum se transform variabila de tip String din request in parametrul LastName din Clasa UpdateTaskRequest???

        try {
         List<Agenda> contacts = agendaService.getAgenda();

         ObjectMapperConfiguration.OBJECT_MAPPER.
                 writeValue(resp.getWriter(), contacts);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was a error while processing your request" + e.getMessage());
        }

    }
}
