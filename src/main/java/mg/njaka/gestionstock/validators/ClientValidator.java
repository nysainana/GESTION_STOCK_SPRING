package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto clientDto) {

        List<String> errors = new ArrayList<>();

        if (clientDto == null) {
            errors.add("Veillez renseigner le nom de client");
            errors.add("Veillez renseigner le prenom de client");
            errors.add("Veillez renseigner l'e-mail de client");
            errors.add("Veillez renseigner le numero de client");
        } else {

            if (!StringUtils.hasLength(clientDto.getNom())) {
                errors.add("Veillez renseigner le nom de client");
            }

            if (!StringUtils.hasLength(clientDto.getPrenom())) {
                errors.add("Veillez renseigner le prenom de client");
            }

            if (!StringUtils.hasLength(clientDto.getEmail())) {
                errors.add("Veillez renseigner l'e-mail de client");
            }

            if (!StringUtils.hasLength(clientDto.getNumTel())) {
                errors.add("Veillez renseigner le numero de client");
            }

//            errors.addAll(AdresseValidator.validate(
//                        clientDto.getAdresse(), "de client"));

        }

        return errors;
    }

}
