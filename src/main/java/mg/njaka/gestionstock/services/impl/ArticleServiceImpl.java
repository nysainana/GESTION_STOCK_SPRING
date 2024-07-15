package mg.njaka.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import mg.njaka.gestionstock.dto.ArticleDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.EntityNotValidException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.model.Article;
import mg.njaka.gestionstock.repository.ArticleRepository;
import mg.njaka.gestionstock.services.ArticleService;
import mg.njaka.gestionstock.validators.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ArticleServiceImpl")
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(
            ArticleRepository articleRepository
    ){
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {

        List<String> errors = ArticleValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("L'article n'est pas valide", dto);
            throw new EntityNotValidException("L'article n'est pas valide",
                    ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        System.out.println("passed");

        return ArticleDto.fromEntity(
                        articleRepository.save(
                                ArticleDto.toEntity(dto)
                        )
        );
    }

    @Override
    public ArticleDto findById(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  null;
        }

        Optional<Article> article = articleRepository.findById(id);

        return articleRepository.findById(id)
                .map(ArticleDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                "Article introvable pour l'id : " + id, ErrorCodes.ARTICLE_NOT_FOUND
        ));

    }

    @Override
    public ArticleDto findByCode(String code) {

        if(!StringUtils.hasLength(code)){
            log.error("Le code article est null");
            return  null;
        }

        return articleRepository.findArticleByCodeArticle(code)
                .map(ArticleDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Article introvable pour le code : " + code, ErrorCodes.ARTICLE_NOT_FOUND
                ));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  false;
        }

        articleRepository.deleteById(id);
        return true;
    }

}
