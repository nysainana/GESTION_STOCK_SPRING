package mg.njaka.gestionstock.dto;

import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.Article;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto{

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private Integer idEntreprise;

    private CategoryDto categorie;

    public static ArticleDto fromEntity(Article obj) {

        if(obj == null){
            return null;
        }

        return ArticleDto.builder()
                .id(obj.getId())
                .codeArticle(obj.getCodeArticle())
                .designation(obj.getDesignation())
                .prixUnitaireHt(obj.getPrixUnitaireHt())
                .tauxTva(obj.getTauxTva())
                .prixUnitaireTtc(obj.getPrixUnitaireTtc())
                .photo(obj.getPhoto())
                .idEntreprise(obj.getIdEntreprise())
                .build();
    }

    public static Article toEntity(ArticleDto obj) {

        if(obj == null){
            return null;
        }

        Article article = new Article();
        article.setId(obj.getId());
        article.setCodeArticle(obj.getCodeArticle());
        article.setDesignation(obj.getDesignation());
        article.setPrixUnitaireHt(obj.getPrixUnitaireHt());
        article.setTauxTva(obj.getTauxTva());
        article.setPrixUnitaireTtc(obj.getPrixUnitaireTtc());
        article.setPhoto(obj.getPhoto());
        article.setIdEntreprise(obj.getIdEntreprise());
        article.setCategorie(CategoryDto.toEntity(obj.getCategorie()));

        return article;
    }
}
