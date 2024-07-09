package org.unclecodes.mysalonsystem.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unclecodes.mysalonsystem.salon_exception.User_NotFound_Exception;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> allClients(){
        return clientRepository.findAll();
    }

    public void addClient(Client client){
        clientRepository.save(client);
    }

    public Optional<Client> getClient(String email){
        return Optional.ofNullable(clientRepository.findByEmail(email)
                .orElseThrow(() -> new User_NotFound_Exception("User with email: " + email + "" +
                        " does not found.")));
    }

    public Optional<Client> getClient(Long id){
        return Optional.ofNullable(clientRepository.findById(id)
                .orElseThrow(() -> new User_NotFound_Exception("User with email: " + id + "" +
                        " does not found.")));
    }

    public void removeClient(String email){
        clientRepository.deleteClientByEmail(email);
    }

    public void deleteClient(String email){
        clientRepository.delete(clientRepository.findByEmail(email).get());
    }
}
