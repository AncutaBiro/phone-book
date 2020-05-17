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

public void createAgenda (CreateAgendaRequest request) throws IOException, SQLException {
    System.out.println("Creating agenda: " + request);
    agendaRepository.createAgenda(request);
}

public void updateAgenda (long id, UpdateAgendaRequest request) throws IOException, SQLException {
    System.out.println("Updating agenda " + id + ":" + request);
    agendaRepository.updateAgenda(id, request);
}

public void deleteAgenda (long id) throws IOException, SQLException {
    System.out.println("Deleting agenda: " + id);
    agendaRepository.deleteAgenda(id);
}

public List<Agenda> getAgenda () throws IOException, SQLException {
    System.out.println("Printing agenda: ");
    return agendaRepository.getAgenda();
}

}
