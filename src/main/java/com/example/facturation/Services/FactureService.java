package com.example.facturation.Services;

import com.example.facturation.DTO.FactureDTO;
import com.example.facturation.DTO.FactureDetailDTO;
import com.example.facturation.DTO.LigneDTO;
import com.example.facturation.Entities.Client;
import com.example.facturation.Entities.Facture;
import com.example.facturation.Entities.Ligne;
import com.example.facturation.Repositories.IFactureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class FactureService {
    private final IFactureRepository factureRepository ;
    private final ClientService clientService ;


    public FactureService(IFactureRepository facRepo  , ClientService clientService){
      factureRepository= facRepo ;
      this.clientService = clientService ;


    }


    public ResponseEntity<String> creerFacture(FactureDTO dto) {
        if (dto.getLignes() == null || dto.getLignes().isEmpty()) {
            return ResponseEntity.badRequest().body("la facture doit contenir au moins une ligne.");
        }

        Facture facture = new Facture();
        facture.setDate(LocalDate.now());

        Client client = clientService.getClientInfo(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client n'existe pas"));
        facture.setClient(client);

        // Lier les lignes à la facture
        List<Ligne> lignes = dto.getLignes().stream().map(l -> {
            Ligne ligne = new Ligne();
            ligne.setDescription(l.getDescription());
            ligne.setQuantite(l.getQuantite());
            ligne.setPrixUnitaireHT(l.getPrixUnitaireHT());
            ligne.setTauxTVA(l.getTauxTVA());
            ligne.setFacture(facture); // liaison ici car cest la meme facture
            return ligne;
        }).toList();

        facture.setLignes(lignes);

        // Enregistrer la facture
        factureRepository.save(facture);

        return ResponseEntity.ok("Facture créée avec succès");
    }


    public Optional<Facture> getFacture(int id){
        return factureRepository.findById(id);
    }

    public FactureDetailDTO getFactureDetails(int factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new RuntimeException("Facture introuvable"));

        List<LigneDTO> ligneDTOs = new ArrayList<>();
        double totalHT = 0;
        double totalTVA = 0;
        // ici on parcours chaque ligne de la facture et on calcule les prix
        for (Ligne ligne : facture.getLignes()) {
            double montantHT = ligne.getQuantite() * ligne.getPrixUnitaireHT();
            double montantTVA = montantHT * ligne.getTauxTVA() / 100;

            totalHT += montantHT;
            totalTVA += montantTVA;

            ligneDTOs.add(new LigneDTO(
                    ligne.getDescription(),
                    ligne.getQuantite(),
                    ligne.getPrixUnitaireHT(),
                    ligne.getTauxTVA(),
                    montantHT,
                    montantTVA
            ));
        }

        // le prix ttc apes la sortie de la boucle
        double totalTTC = totalHT + totalTVA;

        return new FactureDetailDTO(
                facture.getIdFacture(),
                facture.getClient().getNom(),
                facture.getDate(),
                ligneDTOs,
                totalHT,
                totalTVA,
                totalTTC
        );
    }

}
