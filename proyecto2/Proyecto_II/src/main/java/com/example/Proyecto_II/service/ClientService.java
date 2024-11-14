package com.example.Proyecto_II.service;

import com.example.Proyecto_II.model.Client;
import java.util.List;

public interface ClientService {

    public Client addClient(Client client);
    public List<Client> listClients();
    public Client getClientById(int  id);
    Client updateClient(int  id, Client  updatedCLient);
    public Client deleteClient(int  clientId);
    void incrementAccountCount(int  clientId);
    void decrementAccountCount(int  clientId);
}
