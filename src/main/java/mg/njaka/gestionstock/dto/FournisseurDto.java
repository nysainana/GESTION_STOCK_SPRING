package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.Fournisseur;

import java.util.List;


@Data
@Builder
public class FournisseurDto{

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String numTel;

    private String photo;

    private Integer idEntreprise;

    private AdresseDto adresse;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur obj) {

        if(obj == null){
            return null;
        }

        return FournisseurDto.builder()
                .id(obj.getId())
                .nom(obj.getNom())
                .prenom(obj.getPrenom())
                .email(obj.getEmail())
                .numTel(obj.getNumTel())
                .photo(obj.getPhoto())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static Fournisseur toEntity(FournisseurDto obj) {

        if(obj == null){
            return null;
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(obj.getId());
        fournisseur.setNom(obj.getNom());
        fournisseur.setPrenom(obj.getPrenom());
        fournisseur.setEmail(obj.getEmail());
        fournisseur.setNumTel(obj.getNumTel());
        fournisseur.setPhoto(obj.getPhoto());
        fournisseur.setIdEntreprise(obj.getIdEntreprise());

        return fournisseur;
    }
}
