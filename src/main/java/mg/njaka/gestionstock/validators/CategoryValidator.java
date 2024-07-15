package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categorieDto){

        List<String> errors = new ArrayList<>();

        if(categorieDto == null || !StringUtils.hasLength(categorieDto.getCodeCategorie())){
            errors.add("Veillez renseigner le code de la categorie");
        }

        return errors;
    }

}
