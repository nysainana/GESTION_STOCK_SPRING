package mg.njaka.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Utilisateur extends AbstractEntities {

    private String codeUtilisateur;

    private String nomUtilisateur;

    private String prenomUtilisateur;

    private String email;

    private Instant dateNaisance;

    private String motDePasse;

    private String photo;

    @Embedded
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "utilisateur")
    private List<Role> roles;

}
