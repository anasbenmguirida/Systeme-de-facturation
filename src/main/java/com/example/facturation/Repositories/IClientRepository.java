package com.example.facturation.Repositories;

import com.example.facturation.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client , Integer> {


}
