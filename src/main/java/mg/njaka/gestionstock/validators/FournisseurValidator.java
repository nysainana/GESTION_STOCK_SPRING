package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto) {

        List<String> errors = new ArrayList<>();

        if (fournisseurDto == null) {
            errors.add("Veillez renseigner le nom de fournisseur");
            errors.add("Veillez renseigner le prenom de fournisseur");
            errors.add("Veillez renseigner l'e-mail de fournisseur");
            errors.add("Veillez renseigner le numero de fournisseur");
        }
        else {

            if (!StringUtils.hasLength(fournisseurDto.getNom())) {
                errors.add("Veillez renseigner le nom de fournisseur");
            }

            if (!StringUtils.hasLength(fournisseurDto.getPrenom())) {
                errors.add("Veillez renseigner le prenom de fournisseur");
            }

            if (!StringUtils.hasLength(fournisseurDto.getEmail())) {
                errors.add("Veillez renseigner l'e-mail de fournisseur");
            }

            if (!StringUtils.hasLength(fournisseurDto.getNumTel())) {
                errors.add("Veillez renseigner le numero de fournisseur");
            }

        }

        return errors;
    }

}
