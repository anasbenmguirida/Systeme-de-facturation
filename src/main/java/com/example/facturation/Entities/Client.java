package com.example.facturation.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Client {
    // cle primaire de la table client => auto increment
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id ;
    private String nom ;
    private String email ;
    private long SIRET ;
    private LocalDate dateCreation ;

    // contrainte : un ckient peut avoir plusieur facture
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Facture> listeFactures ;
}
