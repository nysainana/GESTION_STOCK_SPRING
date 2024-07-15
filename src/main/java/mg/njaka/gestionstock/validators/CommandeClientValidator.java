package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto cc) {

        List<String> errors = new ArrayList<>();
        if (cc == null) {
            errors.add("Veillez renseigner le code de commande");
            errors.add("Date de  commande invalide");
            errors.add("Veillez renseigner le client");
        } else {

            if (!StringUtils.hasLength(cc.getCodeCC())) {
                errors.add("Veillez renseigner le code de commande");
            }

            if (cc.getDateCC() == null) {
                errors.add("Date de  commande invalide");
            }

            if(cc.getClient() == null){
                errors.add("Veillez renseigner le client");
            }

            if(cc.getClient() != null && cc.getClient().getId() == null){
                errors.add("Le client est invalide");
            }

        }
        return errors;
    }

}
