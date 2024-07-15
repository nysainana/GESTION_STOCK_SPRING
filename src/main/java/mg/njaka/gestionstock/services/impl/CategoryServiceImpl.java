package mg.njaka.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import mg.njaka.gestionstock.dto.CategoryDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.EntityNotValidException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.model.Category;
import mg.njaka.gestionstock.repository.CategoryRepository;
import mg.njaka.gestionstock.services.CategoryService;
import mg.njaka.gestionstock.validators.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository
    ){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {

        List<String> errors = CategoryValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("Le categorie n'est pas valide", dto);
            throw new EntityNotValidException("Le categorie n'est pas valide",
                    ErrorCodes.CATEGORIE_NOT_VALID, errors);
        }

        return CategoryDto.fromEntity(
                categoryRepository.save(
                        CategoryDto.toEntity(dto)
                )
        );
    }

    @Override
    public CategoryDto findById(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  null;
        }

        return  categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categorie introvable pour l'id : " + id, ErrorCodes.CATEGORIE_NOT_FOUND
                ));
    }

    @Override
    public CategoryDto findByCode(String code) {

        if(!StringUtils.hasLength(code)){
            log.error("Le code article est null");
            return  null;
        }

        Optional<Category> categorie = categoryRepository.findCategoriesByCodeCategorie(code);

        return categoryRepository.findCategoriesByCodeCategorie(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categorie introvable pour le code : " + code, ErrorCodes.CATEGORIE_NOT_FOUND
                ));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  false;
        }

        categoryRepository.deleteById(id);
        return true;
    }
}
