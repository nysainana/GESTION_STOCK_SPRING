package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto dto);

    ArticleDto findById(Integer id);

    ArticleDto findByCode(String code);

    List<ArticleDto> findAll();

    boolean delete(Integer id);

}
