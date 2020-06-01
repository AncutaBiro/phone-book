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

public void createAgenda (CreateAgendaRequest request) throws IOException, SQLException, ClassNotFoundException {
    System.out.println("Creating agenda: " + request);
    agendaRepository.createAgenda(request);
}

public void updateAgenda (long id, UpdateAgendaRequest request) throws IOException, SQLException, ClassNotFoundException {
    System.out.println("Updating agenda : " + id + request);
    agendaRepository.updateAgenda(id, request);
}

public void deleteAgenda (Long id1) throws IOException, SQLException, ClassNotFoundException {
    System.out.println("Deleting agenda: " + id1);
    agendaRepository.deleteAgenda(id1);
}

public void deleteAgenda (Long id1, Long id2) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting agenda: " + id1 + id2);
        agendaRepository.deleteAgenda(id1, id2);
}

public List<Agenda> getAgenda (String lastName) throws IOException, SQLException, ClassNotFoundException {
    System.out.println("Printing agenda: ");

    if (lastName == null) {
    return agendaRepository.getAgenda();
    } else {
        return agendaRepository.getAgenda(lastName);
    }
}

}
