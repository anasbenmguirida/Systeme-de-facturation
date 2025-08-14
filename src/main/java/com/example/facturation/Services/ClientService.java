package com.example.facturation.Services;

import com.example.facturation.Entities.Client;
import com.example.facturation.Repositories.IClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final IClientRepository repository ;

    // injection de dépandance via le constructeur
    public ClientService(IClientRepository repo){
        this.repository = repo ;
    }

    public ResponseEntity<String> create(Client client){
        client.setDateCreation(LocalDate.now());
        try {
            repository.save(client) ;
            return ResponseEntity.ok("client enregistre avec succes") ;
        } catch(Exception e){
           return  ResponseEntity.ok("erreur lors la creation");
        }

    }
    // detail d'un client specific identifie par son id
    public Optional<Client> getClientInfo(int id){
        return repository.findById(id) ;
    }

    // detail de tous les clients 
    public List<Client> getAllClients(){
        return repository.findAll() ;
    }
}
