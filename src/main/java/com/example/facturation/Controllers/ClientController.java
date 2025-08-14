package com.example.facturation.Controllers;

import com.example.facturation.Entities.Client;
import com.example.facturation.Services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService service ;

    public ClientController(ClientService clientService){
        service = clientService ;
    }


    @PostMapping
    public ResponseEntity<String> save(@RequestBody Client client){
        return service.create(client) ;
    }
    @GetMapping("{id}")
    public Optional<Client> getClientById(@PathVariable  int id){
        return service.getClientInfo(id)  ;
    }

    @GetMapping
    public List<Client> getAllClients(){
        return service.getAllClients() ;
    }
}
