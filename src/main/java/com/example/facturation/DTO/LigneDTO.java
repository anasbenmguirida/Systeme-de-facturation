package com.example.facturation.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneDTO {

    private String description;
    private int quantite;
    private double prixUnitaireHT;
    private double tauxTVA;
    private double montantHT;
    private double montantTVA;

}
