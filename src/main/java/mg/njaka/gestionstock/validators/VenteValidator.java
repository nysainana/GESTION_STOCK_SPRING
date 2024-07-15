package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.VenteDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {

    public static List<String> validate(VenteDto venteDto) {

        List<String> errors = new ArrayList<>();

        if (venteDto == null) {

        }
        else {

            if (!StringUtils.hasLength(venteDto.getCodeVente())) {
                errors.add("Veillez renseigner le code du vente");
            }

            if (venteDto.getDateVente() == null) {
                errors.add("Veillez renseigner la date du vente");
            }

        }

        return errors;
    }

}
