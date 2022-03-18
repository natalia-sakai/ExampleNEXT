package br.com.ibm.example.controller;

import br.com.ibm.example.domain.ClientDto;
import br.com.ibm.example.entity.Client;
import br.com.ibm.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(this.service.get());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(this.service.get(id));
    }

    @RequestMapping(path = "/name", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getByName(@Param(value = "name") String name) {
        return ResponseEntity.ok(this.service.getByName(name));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Client> save(@RequestBody ClientDto dto) {
        //todo
        //perguntar sobre como utilizar esse tipo de return
        return new ResponseEntity<>(this.service.save(dto), CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClientDto> update(@PathVariable(value = "id") Long id, @RequestBody ClientDto dto) {
        service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

}
