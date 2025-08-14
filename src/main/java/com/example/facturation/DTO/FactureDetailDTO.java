package com.example.facturation.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FactureDetailDTO {
        private int idFacture;
        private String nomClient;
        private LocalDate date;
        private List<LigneDTO> lignes;
        private double totalHT;
        private double totalTVA;
        private double totalTTC;
    }





