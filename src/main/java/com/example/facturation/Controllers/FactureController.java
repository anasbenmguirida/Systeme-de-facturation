package com.example.facturation.Controllers;

import com.example.facturation.DTO.FactureDTO;
import com.example.facturation.DTO.FactureDetailDTO;
import com.example.facturation.Services.FactureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/facture")
public class FactureController {

    private final FactureService service  ;

    public FactureController(FactureService factureService){
        service = factureService  ;
    }
    @PostMapping
    public ResponseEntity<String> creerFacture(@RequestBody FactureDTO factureDTO){
        return service.creerFacture(factureDTO) ;
    }

    // ici je retourne le deatil d'une facture
    @GetMapping("/{id}/details")
    public ResponseEntity<FactureDetailDTO> getFactureDetails(@PathVariable int id) {
        return ResponseEntity.ok(service.getFactureDetails(id));
    }





}
