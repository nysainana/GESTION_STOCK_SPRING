package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.RoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {

    public static List<String> validate(RoleDto roleDto) {

        List<String> errors = new ArrayList<>();

        if (roleDto == null) {
            errors.add("Veillez renseigner le code de role");
            errors.add("Veillez renseigner le nom de role");
        }
        else {

            if (!StringUtils.hasLength(roleDto.getCodeRole())) {
                errors.add("Veillez renseigner le code de role");
            }

            if (!StringUtils.hasLength(roleDto.getNomRole())) {
                errors.add("Veillez renseigner le nom de role");
            }

        }

        return errors;
    }

}
