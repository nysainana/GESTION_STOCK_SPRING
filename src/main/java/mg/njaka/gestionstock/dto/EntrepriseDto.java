package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.Entreprise;

import java.util.List;

@Data
@Builder
public class EntrepriseDto{

    private Integer id;

    private String nomEntreprise;

    private String description;

    private String codeFiscale;

    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;

    private Integer idEntreprise;

    private AdresseDto adresse;

    @JsonIgnore
    private List<UtilisateurDto> utilisateurs;

    public static EntrepriseDto fromEntity(Entreprise obj) {

        if(obj == null){
            return  null;
        }

        return EntrepriseDto.builder()
                .id(obj.getId())
                .nomEntreprise(obj.getNomEntreprise())
                .description(obj.getDescription())
                .codeFiscale(obj.getCodeFiscale())
                .photo(obj.getPhoto())
                .email(obj.getEmail())
                .numTel(obj.getNumTel())
                .siteWeb(obj.getSiteWeb())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static Entreprise toEntity(EntrepriseDto obj) {

        if(obj == null){
            return  null;
        }

        Entreprise entreprise = new Entreprise();
        entreprise.setId(obj.getId());
        entreprise.setNomEntreprise(obj.getNomEntreprise());
        entreprise.setDescription(obj.getDescription());
        entreprise.setCodeFiscale(obj.getCodeFiscale());
        entreprise.setPhoto(obj.getPhoto());
        entreprise.setNumTel(obj.getNumTel());
        entreprise.setSiteWeb(obj.getSiteWeb());
        entreprise.setIdEntreprise(obj.getIdEntreprise());

        return entreprise;
    }
}
