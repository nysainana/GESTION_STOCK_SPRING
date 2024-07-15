package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.MvmtStockDto;

import java.util.ArrayList;
import java.util.List;

public class MvmtStockValidator {

    public static List<String> validate(MvmtStockDto mvmtStockDto) {

        List<String> errors = new ArrayList<>();

        if (mvmtStockDto == null) {
            errors.add("Veillez renseigner la quantite");
            errors.add("Veillez renseigner la date");
        }else{

            if (mvmtStockDto.getQuantite() == null) {
                errors.add("Veillez renseigner la quantite");
            }

            if (mvmtStockDto.getDateMvmtStock() == null) {
                errors.add("Veillez renseigner la date");
            }

        }

        return errors;
    }

}
