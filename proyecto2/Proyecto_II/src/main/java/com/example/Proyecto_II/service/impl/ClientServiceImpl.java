package com.example.Proyecto_II.service.impl;

import com.example.Proyecto_II.model.Account;
import com.example.Proyecto_II.model.Client;
import com.example.Proyecto_II.repository.ClientRepository;
import com.example.Proyecto_II.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addClient(Client client) {
        if(clientRepository.existsByDni(client.getDni())){
            System.out.println("El DNI ya se encuentra registrado");
            return null;
        }
        return clientRepository.save(client);
    }
    @Override
    public List<Client> listClients() {
        return clientRepository.findAll().stream()
                .filter(client -> client.getAccountCount() >= 0)
                .collect(Collectors.toList());
    }

    public Client getClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }
    public Client updateClient(int id, Client updatedClient){
        Client foundClient = getClientById(id);
        foundClient.setFirst_name(updatedClient.getFirst_name());
        foundClient.setLast_name(updatedClient.getLast_name());
        foundClient.setEmail(updatedClient.getEmail());
        return clientRepository.save(foundClient);
    }

    public  Client deleteClient(int  clientId){
        Client client = getClientById(clientId);

        if (client.getAccountCount() > 0) {
            throw new IllegalStateException("No se puede eliminar el cliente porque tiene cuentas activas.");
        }

        clientRepository.delete(client);
        return client;
    }
    @Override
    public void incrementAccountCount(int  clientId) {
        Client client = getClientById(clientId);
        client.setAccountCount(client.getAccountCount() + 1);
        clientRepository.save(client);
    }


    @Override
    public void decrementAccountCount(int  clientId) {
        Client client = getClientById(clientId);
        if (client.getAccountCount() > 0) {
            client.setAccountCount(client.getAccountCount() - 1);
            clientRepository.save(client);
        }
    }
}
