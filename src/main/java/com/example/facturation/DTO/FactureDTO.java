package com.example.facturation.DTO;

import com.example.facturation.Entities.Ligne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class FactureDTO {

    private int clientId ;
    private List<Ligne> lignes = new ArrayList<>() ;
}
