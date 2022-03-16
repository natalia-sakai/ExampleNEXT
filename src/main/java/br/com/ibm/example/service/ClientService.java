package br.com.ibm.example.service;

import br.com.ibm.example.domain.ClientDto;
import br.com.ibm.example.entity.Client;
import br.com.ibm.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> get() { return repository.findAll(); }

    public Client get(Long id) {
        return repository.getOne(id);
    }

    public List<Client> getByName(String name) {
        return repository.findByName(name);
    }

    public Client save(ClientDto dto) {
        Client client = new Client(dto.getId(), dto.getName());
        return repository.save(client);
    }

    public void delete(Long id) { repository.deleteById(id); }

    public Client update(Long id, ClientDto newData) {
        Client entity = repository.getOne(id);
        updateData(entity, newData);
        return repository.save(entity);
    }

    private void updateData(Client entity, ClientDto newData) {
        entity.setName(newData.getName());
    }
}
