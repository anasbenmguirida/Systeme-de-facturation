package com.example.facturation.Entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Ligne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String description ;
    private int quantite ;
    private double prixUnitaireHT ;
    private double tauxTVA ;

    @ManyToOne
    @JoinColumn(name = "factureId", nullable = false)
    private Facture facture ;
}
