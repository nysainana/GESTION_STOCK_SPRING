package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.Entreprise;
import mg.njaka.gestionstock.model.Utilisateur;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {

    private Integer id;

    private String codeUtilisateur;

    private String nomUtilisateur;

    private String prenomUtilisateur;

    private String email;

    private Instant dateNaisance;

    private String motDePasse;

    private String photo;

    private AdresseDto adresse;

    @JsonIgnore
    private Entreprise entreprise;

    @JsonIgnore
    private List<RoleDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur obj) {

        if(obj == null){
            return null;
        }

        return UtilisateurDto.builder()
                .id(obj.getId())
                .codeUtilisateur(obj.getCodeUtilisateur())
                .nomUtilisateur(obj.getNomUtilisateur())
                .prenomUtilisateur(obj.getPrenomUtilisateur())
                .email(obj.getEmail())
                .dateNaisance(obj.getDateNaisance())
                .motDePasse(obj.getMotDePasse())
                .photo(obj.getPhoto())
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto obj) {

        if(obj == null){
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(obj.getId());
        utilisateur.setCodeUtilisateur(obj.getCodeUtilisateur());
        utilisateur.setNomUtilisateur(obj.getNomUtilisateur());
        utilisateur.setPrenomUtilisateur(obj.getPrenomUtilisateur());
        utilisateur.setEmail(obj.getEmail());
        utilisateur.setDateNaisance(obj.getDateNaisance());
        utilisateur.setMotDePasse(obj.getMotDePasse());
        utilisateur.setPhoto(obj.getPhoto());

        return utilisateur;
    }
}
