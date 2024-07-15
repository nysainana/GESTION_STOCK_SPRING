package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.Role;

@Data
@Builder
public class RoleDto{

    private Integer id;

    private String codeRole;

    private String nomRole;

    private Integer idEntreprise;

    @JsonIgnore
    private UtilisateurDto utilisateur;

    public static RoleDto fromEntity(Role obj) {

        if(obj == null){
            return null;
        }

        return RoleDto.builder()
                .id(obj.getId())
                .codeRole(obj.getCodeRole())
                .nomRole(obj.getNomRole())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static Role toEntity(RoleDto obj) {

        if(obj == null){
            return null;
        }

        Role role = new Role();
        role.setId(obj.getId());
        role.setCodeRole(obj.getCodeRole());
        role.setNomRole(obj.getNomRole());
        role.setIdEntreprise(obj.getIdEntreprise());

        return role;
    }
}
