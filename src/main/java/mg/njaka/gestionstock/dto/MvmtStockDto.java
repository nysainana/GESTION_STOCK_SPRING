package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.MvmtStock;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvmtStockDto{

    private Integer id;

    private Instant dateMvmtStock;

    private BigDecimal quantite;

    private Integer idEntreprise;

    private TypeMvmtStockDto typeMvmtStock;

    @JsonIgnore
    private ArticleDto article;

    public static MvmtStockDto fromEntity(MvmtStock obj) {

        if(obj == null){
            return null;
        }

        return MvmtStockDto.builder()
                .id(obj.getId())
                .dateMvmtStock(obj.getDateMvmtStock())
                .quantite(obj.getQuantite())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static MvmtStock toEntity(MvmtStockDto obj) {

        if(obj == null){
            return null;
        }

        MvmtStock mvmtStock = new MvmtStock();
        mvmtStock.setId(obj.getId());
        mvmtStock.setDateMvmtStock(obj.getDateMvmtStock());
        mvmtStock.setQuantite(obj.getQuantite());
        mvmtStock.setIdEntreprise(obj.getIdEntreprise());

        return mvmtStock;
    }
}
