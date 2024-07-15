package mg.njaka.gestionstock.controllers;

import mg.njaka.gestionstock.controllers.api.ArticleApi;
import mg.njaka.gestionstock.dto.ArticleDto;
import mg.njaka.gestionstock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Autowired
    public ArticleController(
            ArticleService articleService
    ){
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCode(String idArticle) {
        return articleService.findByCode(idArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return articleService.delete(id);
    }
}
