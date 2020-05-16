package org.fasttrackit;

import org.fasttrackit.persistence.AgendaRepository;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, SQLException {

//        CreateAgendaRequest request = new CreateAgendaRequest();
//        request.setFirstName("Ancuta");
//        request.setLastName("Biro");
//        request.setPhoneNumber("0740000000");
//
        AgendaRepository repository = new AgendaRepository();
//        repository.createAgenda(request);

//        UpdateAgendaRequest request1 = new UpdateAgendaRequest();
//        request1.setFirstName("Ancuta");
//        request1.setFirstName("ANCUTA");

        repository.deleteAgenda(1);

    }
}
