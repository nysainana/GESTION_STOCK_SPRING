package mg.njaka.gestionstock.repository;

import mg.njaka.gestionstock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /*************************************************************

    @Query("select a from article where " +
            "code_article = :code and designation = :desi")
    Article findByCustomQuerry(
            @Param("code") String code, @Param("desi") String designation);

    @Query( value = "select * from article where code_article = :code")
    Article findByCustomNativeQuerry(@Param("code") String code);

    Article findByCodeArticleIgnoreCaseAndDesignationIgnoreCase
            (String codeArticle, String designation);

     *************************************************************/

    Optional<Article> findArticleByCodeArticle(String code);

}
