package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.Client;

import java.util.List;

@Data
@Builder
public class ClientDto{

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String numTel;

    private String photo;

    private Integer idEntreprise;

    private AdresseDto adresse;

    @JsonIgnore
    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client obj) {

        if(obj == null){
            return null;
        }

        return ClientDto.builder()
                .id(obj.getId())
                .nom(obj.getNom())
                .prenom(obj.getPrenom())
                .email(obj.getEmail())
                .numTel(obj.getNumTel())
                .photo(obj.getPhoto())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static Client toEntity(ClientDto obj) {

        if(obj == null){
            return null;
        }

        Client client = new Client();
        client.setId(obj.getId());
        client.setNom(obj.getNom());
        client.setPrenom(obj.getPrenom());
        client.setEmail(obj.getEmail());
        client.setNumTel(obj.getNumTel());
        client.setPhoto(obj.getPhoto());
        client.setIdEntreprise(obj.getIdEntreprise());

        return client;
    }
}
