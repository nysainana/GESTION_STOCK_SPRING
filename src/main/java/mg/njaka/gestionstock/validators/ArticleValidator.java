package mg.njaka.gestionstock.validators;

import mg.njaka.gestionstock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto) {

        List<String> errors = new ArrayList<>();
        if(articleDto == null){
            errors.add("Veillez renseigner le code de l'article");
            errors.add("Veillez renseigner le designation de l'article");
            errors.add("Veillez renseigner le prix unitaire HT de l'article");
            errors.add("Veillez renseigner le taux TVA de l'article");
            errors.add("Veillez renseigner le prix unitaire TTC de l'article");
            errors.add("Veillez selectioner une categorie");
        }else {

            if (!StringUtils.hasLength(articleDto.getCodeArticle())) {
                errors.add("Veillez renseigner le code de l'article");
            }

            if (!StringUtils.hasLength(articleDto.getDesignation())) {
                errors.add("Veillez renseigner le designation de l'article");
            }

            if (articleDto.getPrixUnitaireHt() == null) {
                errors.add("Veillez renseigner le prix unitaire HT de l'article");
            }

            if (articleDto.getTauxTva() == null) {
                errors.add("Veillez renseigner le taux TVA de l'article");
            }

            if (articleDto.getPrixUnitaireTtc() == null) {
                errors.add("Veillez renseigner le prix unitaire TTC de l'article");
            }

            if (articleDto.getCategorie() == null) {
                errors.add("Veillez selectioner une categorie");
            }

        }
        return errors;
    }

}
