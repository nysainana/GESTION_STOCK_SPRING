package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.LigneCommandeClient;

import java.math.BigDecimal;


@Data
@Builder
public class LigneCommandeClientDto{

    private Integer id;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    @JsonIgnore
    private ArticleDto article;

    @JsonIgnore
    private CommandeClientDto commandeClient;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient obj) {

        if(obj == null){
            return null;
        }

        return LigneCommandeClientDto.builder()
                .id(obj.getId())
                .quantite(obj.getQuantite())
                .prixUnitaire(obj.getPrixUnitaire())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto obj) {

        if(obj == null){
            return null;
        }

        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(obj.getId());
        ligneCommandeClient.setQuantite(obj.getQuantite());
        ligneCommandeClient.setPrixUnitaire(obj.getPrixUnitaire());
        ligneCommandeClient.setIdEntreprise(obj.getIdEntreprise());

        return ligneCommandeClient;
    }
}
