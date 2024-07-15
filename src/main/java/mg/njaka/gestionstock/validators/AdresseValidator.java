package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.AdresseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {

    public static List<String> validate(AdresseDto adresseDto, String prefix){

        List<String> errors = new ArrayList<>();

        if(adresseDto == null){
            errors.add("Veillez renseigner l'adresse " + prefix);
        }
        else {

            if (!StringUtils.hasLength(adresseDto.getAdresse1())) {
                errors.add("Veillez renseigner l'adresse1 " + prefix);
            }

            if (!StringUtils.hasLength(adresseDto.getCodePostal())) {
                errors.add("Veillez renseigner le code postal " + prefix);
            }

            if (!StringUtils.hasLength(adresseDto.getPays())) {
                errors.add("Veillez renseigner le pays " + prefix);
            }

            if (!StringUtils.hasLength(adresseDto.getVille())) {
                errors.add("Veillez renseigner le ville " + prefix);
            }
        }

        return errors;
    }

}
