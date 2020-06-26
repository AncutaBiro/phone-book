package org.fasttrackit.web;

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
import java.util.Arrays;
import java.util.List;

@WebServlet("/agenda")
public class AgendaServlet extends HttpServlet {

    private AgendaService agendaService = new AgendaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);

        CreateAgendaRequest request = ObjectMapperConfiguration.OBJECT_MAPPER
                .readValue(req.getReader(), CreateAgendaRequest.class);

        try {
            agendaService.createAgenda(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was a error while processing your request" + e.getMessage());
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);

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
        addCorsHeaders(resp);

        String id1 = req.getParameter("id");
//        String[] ids = req.getParameterValues("id");

        try {
//            if( ids == null) {
            agendaService.deleteAgenda(Long.parseLong(id1));
//            } else {
//                agendaService.deleteAgenda(Long.parseLong(Arrays.toString(ids));}
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was a error while processing your request" + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);

        String lastName = req.getParameter("lastName");

        try {
            List<Agenda> contacts = agendaService.getAgenda(lastName);
            ObjectMapperConfiguration.OBJECT_MAPPER.
                    writeValue(resp.getWriter(), contacts);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was a error while processing your request" + e.getMessage());
        }

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);
    }

    private void addCorsHeaders(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        resp.addHeader("Access-Control-Allow-Headers", "content-type");

    }
}
