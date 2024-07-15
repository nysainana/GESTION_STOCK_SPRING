package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.LigneCommandeFournisseur;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto{

    private Integer id;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    @JsonIgnore
    private ArticleDto article;

    @JsonIgnore
    private CommandeFournisseurDto commandeFournisseur;

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur obj) {

        if(obj == null){
            return null;
        }

        return LigneCommandeFournisseurDto.builder()
                .id(obj.getId())
                .quantite(obj.getQuantite())
                .prixUnitaire(obj.getPrixUnitaire())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto obj) {

        if(obj == null){
            return null;
        }

        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(obj.getId());
        ligneCommandeFournisseur.setQuantite(obj.getQuantite());
        ligneCommandeFournisseur.setPrixUnitaire(obj.getPrixUnitaire());
        ligneCommandeFournisseur.setIdEntreprise(obj.getIdEntreprise());

        return ligneCommandeFournisseur;
    }
}
