package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.CommandeFournisseur;

import java.time.Instant;
import java.util.List;


@Data
@Builder
public class CommandeFournisseurDto{

    private Integer id;

    private String codeCF;

    private Instant dateCF;

    private Integer idEntreprise;

    @JsonIgnore
    private FournisseurDto fournisseurDto;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseur;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur obj) {

        if(obj == null){
            return null;
        }

        return CommandeFournisseurDto.builder()
                .id(obj.getId())
                .codeCF(obj.getCodeCF())
                .dateCF(obj.getDateCF())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto obj) {

        if(obj == null){
            return null;
        }

        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(obj.getId());
        commandeFournisseur.setCodeCF(obj.getCodeCF());
        commandeFournisseur.setDateCF(obj.getDateCF());
        commandeFournisseur.setIdEntreprise(obj.getIdEntreprise());

        return commandeFournisseur;
    }
}
