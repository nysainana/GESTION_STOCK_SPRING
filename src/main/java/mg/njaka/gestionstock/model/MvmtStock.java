package mg.njaka.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MvmtStock extends AbstractEntities{

    private Instant dateMvmtStock;

    private BigDecimal quantite;

    private Integer idEntreprise;

    private TypeMvmtStock typeMvmtStock;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

}
