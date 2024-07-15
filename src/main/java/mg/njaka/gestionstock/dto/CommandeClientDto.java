package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.CommandeClient;
import mg.njaka.gestionstock.model.EtatCommande;

import java.time.Instant;
import java.util.List;


@Data
@Builder
public class CommandeClientDto{

    private Integer id;

    private String codeCC;

    private Instant dateCC;

    private Integer idEntreprise;

    private EtatCommande etatCommande;

    @JsonIgnore
    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient obj) {

        if(obj == null){
            return null;
        }

        return CommandeClientDto.builder()
                .id(obj.getId())
                .codeCC(obj.getCodeCC())
                .dateCC(obj.getDateCC())
                .idEntreprise(obj.getIdEntreprise())
                .etatCommande(obj.getEtatCommande())
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto obj) {

        if(obj == null){
            return null;
        }

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(obj.getId());
        commandeClient.setCodeCC(obj.getCodeCC());
        commandeClient.setDateCC(obj.getDateCC());
        commandeClient.setIdEntreprise(obj.getIdEntreprise());
        commandeClient.setEtatCommande(obj.getEtatCommande());

        return commandeClient;
    }
}
