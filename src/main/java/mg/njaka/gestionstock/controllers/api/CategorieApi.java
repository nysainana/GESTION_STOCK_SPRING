package mg.njaka.gestionstock.controllers.api;

import mg.njaka.gestionstock.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mg.njaka.gestionstock.utils.Constants.APP_ROOT;

public interface CategorieApi {


    @PostMapping(value = APP_ROOT + "/categorie/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categorie/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/categorie/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode(@PathVariable("code") String code);

    @GetMapping(value = APP_ROOT + "/categorie/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categorie/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);

}
