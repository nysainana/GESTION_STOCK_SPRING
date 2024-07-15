package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto dto);

    CategoryDto findById(Integer id);

    CategoryDto findByCode(String code);

    List<CategoryDto> findAll();

    boolean delete(Integer id);

}
