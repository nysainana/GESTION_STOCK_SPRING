package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto) {

        List<String> errors = new ArrayList<>();

        if (entrepriseDto == null) {
            errors.add("Veillez renseigner le nom de l'entreprise");
            errors.add("Veillez renseigner la description de l'entreprise");
            errors.add("Veillez renseigner le code fiscale de l'entreprise");
            errors.add("Veillez renseigner l'e-mail de l'entreprise");
            errors.add("Veillez renseigner le numero de l'entreprise");
            errors.add("Veillez renseigner le site web de l'entreprise");
        }
        else{

            if (!StringUtils.hasLength(entrepriseDto.getNomEntreprise())) {
                errors.add("Veillez renseigner le nom de l'entreprise");
            }

            if (!StringUtils.hasLength(entrepriseDto.getDescription())) {
                errors.add("Veillez renseigner la description de l'entreprise");
            }

            if (!StringUtils.hasLength(entrepriseDto.getCodeFiscale())) {
                errors.add("Veillez renseigner le code fiscale de l'entreprise");
            }

            if (!StringUtils.hasLength(entrepriseDto.getEmail())) {
                errors.add("Veillez renseigner l'e-mail de l'entreprise");
            }

            if (!StringUtils.hasLength(entrepriseDto.getNumTel())) {
                errors.add("Veillez renseigner le numero de l'entreprise");
            }

            if (!StringUtils.hasLength(entrepriseDto.getSiteWeb())) {
                errors.add("Veillez renseigner le site web de l'entreprise");
            }

            errors.addAll(AdresseValidator.validate(
                    entrepriseDto.getAdresse(), "de l'entreprise"));

        }

        return errors;
    }

}
