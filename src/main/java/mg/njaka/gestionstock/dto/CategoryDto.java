package mg.njaka.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import mg.njaka.gestionstock.model.Category;

import java.util.List;


@Data
@Builder
public class CategoryDto {

    private Integer id;

    private String codeCategorie;

    private String designation;

    private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDto> articles;

    public static CategoryDto fromEntity(Category categorie){

        if(categorie == null){
            return  null;
        }

        return CategoryDto.builder()
                .id(categorie.getId())
                .codeCategorie(categorie.getCodeCategorie())
                .designation(categorie.getDesignation())
                .idEntreprise(categorie.getIdEntreprise())
                .build();
    }

    public static Category toEntity(CategoryDto categorieDto){

        if(categorieDto == null){
            return null;
        }

        Category categorie = new Category();
        categorie.setId(categorieDto.getId());
        categorie.setCodeCategorie(categorieDto.getCodeCategorie());
        categorie.setDesignation(categorieDto.getDesignation());
        categorie.setIdEntreprise(categorieDto.getIdEntreprise());

        return categorie;
    }

}
