package br.com.ibm.example.service;


import br.com.ibm.example.domain.ClientDto;
import br.com.ibm.example.entity.Client;
import br.com.ibm.example.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {

    @InjectMocks
    ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void testGet() {
        List<Client> c = new ArrayList<>();

        Client cOne = new Client(1L, "Jim");
        Client cTwo = new Client(2L, "kolenchiski");
        Client cThree = new Client(3L, "Waugh");

        c.add(cOne);
        c.add(cTwo);
        c.add(cThree);

        when(clientRepository.findAll()).thenReturn(c);

        //test
        List<Client> cList = clientRepository.findAll();

        assertEquals(3, cList.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testTestGet() {
        Client cli = new Client();
        when(clientRepository.getOne(1L)).thenReturn(cli);

        Client c = clientRepository.getOne(1L);
        assertEquals(c, cli);
        verify(clientRepository, times(1)).getOne(1L);
    }

    @Test
    public void testGetByName() {
        List<Client> cli = new ArrayList<>();
        Client cOne = new Client(1L, "Jim");
        cli.add(cOne);

        when(clientRepository.findByName("Jim")).thenReturn(cli);

        List<Client> c = clientRepository.findByName("Jim");
        assertEquals(c, cli);
        verify(clientRepository, times(1)).findByName("Jim");
    }

    @Test
    public void testSave() {
        Client cli = new Client(1L, "Jim");
        when(clientRepository.save(cli)).thenReturn(cli);

        Client c = clientRepository.save(cli);
        assertEquals(c, cli);
        verify(clientRepository, times(1)).save(cli);
    }

    @Test
    public void testDelete() {
        Client c = new Client(1L, "Jim");
        when(clientRepository.findById(c.getId())).thenReturn(Optional.of(c));
        clientService.delete(c.getId());
        verify(clientRepository).deleteById(c.getId());
    }

    @Test
    public void testUpdate() throws ExecutionException, InterruptedException {
        ClientDto cliDto = new ClientDto(1L, "Jim");
        Client cli = new Client(1L, "Jim");
        Client cliUpdated = new Client(1L, "Jin");

        when(clientRepository.getOne(1L)).thenReturn(cli);
        when(clientRepository.save(any(Client.class))).thenReturn(cliUpdated);

        Client expect = clientService.update(1L, cliDto);

        assertEquals("Jin", expect.getName());
        verify(clientRepository, times(1)).getOne(anyLong());
        verify(clientRepository, times(1)).save(any(Client.class));
    }
}