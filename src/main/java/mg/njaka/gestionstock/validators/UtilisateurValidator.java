package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto){

        List<String> errors = new ArrayList<>();

        if(utilisateurDto == null){
            errors.add("Veillez renseigner le code de l'utilisateur");
            errors.add("Veillez renseigner le nom de l'utilisateur");
            errors.add("Veillez renseigner l'e-mail de l'utilisateur");
            errors.add("Veillez renseigner le mot de passe de l'utilisateur");
            errors.add("Veillez renseigner la date de naissance");
        }else {

            if (!StringUtils.hasLength(utilisateurDto.getCodeUtilisateur())) {
                errors.add("Veillez renseigner le code de l'utilisateur");
            }

            if (!StringUtils.hasLength(utilisateurDto.getNomUtilisateur())) {
                errors.add("Veillez renseigner le nom de l'utilisateur");
            }

            if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
                errors.add("Veillez renseigner l'e-mail de l'utilisateur");
            }

            if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
                errors.add("Veillez renseigner le mot de passe de l'utilisateur");
            }

            if (utilisateurDto.getDateNaisance() == null) {
                errors.add("Veillez renseigner la date de naissance");
            }

            errors.addAll(AdresseValidator.validate(
                        utilisateurDto.getAdresse(), "de l'utilisateur"));

        }

        return errors;
    }

}
