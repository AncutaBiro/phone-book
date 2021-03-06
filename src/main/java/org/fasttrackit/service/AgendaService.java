package org.fasttrackit.service;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.persistence.AgendaRepository;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AgendaService {

    private AgendaRepository agendaRepository = new AgendaRepository();

    public void createAgenda(CreateAgendaRequest request) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Creating agenda: " + request);
        agendaRepository.createAgenda(request);
    }

    public void updateAgenda(Long id, UpdateAgendaRequest request) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Updating agenda : " + id + request);
        agendaRepository.updateAgenda(id, request);
    }

    public void deleteAgenda(Long id1) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting agenda: " + id1);
        agendaRepository.deleteAgenda(id1);
    }

    public List<Agenda> getAgenda() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Printing agenda: ");
        return agendaRepository.getAgenda();
    }

    public List<Agenda> getAgenda(Long id) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Printing agenda: ");
        return agendaRepository.getAgenda(id);

    }
}


