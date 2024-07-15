package mg.njaka.gestionstock.dto;

import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.Vente;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VenteDto{

    private Integer id;

    private String codeVente;

    private Instant dateVente;

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVentes;

    public static VenteDto fromEntity(Vente obj) {

        if(obj == null){
            return null;
        }

        return VenteDto.builder()
                .id(obj.getId())
                .codeVente(obj.getCodeVente())
                .dateVente(obj.getDateVente())
                .commentaire(obj.getCommentaire())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static Vente toEntity(VenteDto obj) {

        if(obj == null){
            return null;
        }

        Vente vente = new Vente();
        vente.setId(obj.getId());
        vente.setCodeVente(obj.getCodeVente());
        vente.setDateVente(obj.getDateVente());
        vente.setCommentaire(obj.getCommentaire());
        vente.setIdEntreprise(obj.getIdEntreprise());

        return vente;
    }
}
