package com.example.Proyecto_II.controller;


import com.example.Proyecto_II.model.Client;
import com.example.Proyecto_II.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/clients")
    public Client addClient(@Validated @RequestBody Client client) {
        return clientService.addClient(client);
    }
    @GetMapping("/clients")
    public List<Client> listClients(@RequestHeader Map<String, String> headers){
        return clientService.listClients();
    }
    @GetMapping("/clients/{id}")
    public Client findAccount(@RequestHeader Map<String, String> headers, @PathVariable("id") int id){
        return clientService.getClientById(id);
    }
    @PutMapping("/clients/{id}")
    public Client  updatedAccount(@PathVariable("id") Integer id, @RequestBody Client updatedClient){
        return clientService.updateClient(id, updatedClient);
    }
    @DeleteMapping("/clients/{id}")
    public Client deleteClient(@PathVariable("id") int id){
        return clientService.deleteClient(id);
    }
}
