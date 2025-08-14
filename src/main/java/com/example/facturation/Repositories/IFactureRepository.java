package com.example.facturation.Repositories;

import com.example.facturation.DTO.FactureDTO;
import com.example.facturation.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFactureRepository extends JpaRepository<Facture, Integer> {
}
