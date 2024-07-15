package mg.njaka.gestionstock.dto;

import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.LigneVente;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private VenteDto vente;

    private ArticleDto article;

    private Integer idEntreprise;

    public static LigneVenteDto fromEntity(LigneVente obj) {

        if(obj == null){
            return null;
        }

        return LigneVenteDto.builder()
                .id(obj.getId())
                .quantite(obj.getQuantite())
                .prixUnitaire(obj.getPrixUnitaire())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto obj) {

        if(obj == null){
            return null;
        }

        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(obj.getId());
        ligneVente.setQuantite(obj.getQuantite());
        ligneVente.setPrixUnitaire(obj.getPrixUnitaire());
        ligneVente.setIdEntreprise(obj.getIdEntreprise());

        return ligneVente;
    }
}
