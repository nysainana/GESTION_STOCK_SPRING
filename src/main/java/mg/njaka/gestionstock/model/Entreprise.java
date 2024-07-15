package mg.njaka.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Entreprise extends AbstractEntities {

    private String nomEntreprise;

    private String description;

    @Embedded
    private Adresse adresse;

    private String codeFiscale;

    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;

    private Integer idEntreprise;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;

}
