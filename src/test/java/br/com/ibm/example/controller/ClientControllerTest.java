package br.com.ibm.example.controller;

import br.com.ibm.example.domain.ClientDto;
import br.com.ibm.example.entity.Client;
import br.com.ibm.example.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    MockMvc mvc;

    //inject mocks explora linha a linha
    //@InjectMocks
    //private ClientController ClientController;

    //mock simula o acesso
    @MockBean
    private ClientService service;

    @Test
    public void testGetAll() throws Exception {
        List<Client> c = new ArrayList<>();

        given(service.get()).willReturn(c);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/clients")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void testGetById() throws Exception {
        Client c = new Client();

        when(service.get(anyLong())).thenReturn(c);

        mvc.perform(get("/clients/" + anyLong())).andExpect(status().isOk());
    }

    //todo
    @Test
    public void testGetByName() throws Exception {
        List<Client> c = new ArrayList<>();

        when(service.getByName(anyString())).thenReturn(c);

        mvc.perform(get("/clients/" + anyString())).andExpect(status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        ClientDto cliDto = new ClientDto(1L, "Jim");
        String json = new ObjectMapper().writeValueAsString(cliDto);

        when(service.save(cliDto)).thenReturn(new Client());

        mvc.perform(post("/clients").content(json)).andExpect(status().isCreated());
    }

    @Test
    public void testDelete() throws Exception {
        ClientDto cliDto = new ClientDto(1L, "Jim");

        doNothing().when(service).delete(cliDto.getId());

        mvc.perform(delete("/clients/" + cliDto.getId()))
        .andExpect(status().isNoContent());

    }

    @Test
    public void testUpdate() throws Exception{
        ClientDto cliDto = new ClientDto(1L, "Jin");
        Client cli = new Client(1L, "Jin");
        String json = new ObjectMapper().writeValueAsString(cli);

        when(service.update(cliDto.getId(), cliDto)).thenReturn(cli);

        mvc.perform(put("/clients/" + cli.getId())
            .content(json))
        .andExpect(status().isOk());
    }
}