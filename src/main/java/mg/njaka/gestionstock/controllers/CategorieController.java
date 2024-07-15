package mg.njaka.gestionstock.controllers;

import mg.njaka.gestionstock.controllers.api.CategorieApi;
import mg.njaka.gestionstock.dto.CategoryDto;
import mg.njaka.gestionstock.services.CategoryService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieController implements CategorieApi {

    private CategoryService categoryService;

    public CategorieController(
            CategoryService categoryService
    ){
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        return categoryService.save(dto);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryService.findById(id);
    }

    @Override
    public CategoryDto findByCode(String code) {
        return categoryService.findByCode(code);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return categoryService.delete(id);
    }
}
