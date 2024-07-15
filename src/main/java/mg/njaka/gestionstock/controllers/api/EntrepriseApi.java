package mg.njaka.gestionstock.controllers.api;

import mg.njaka.gestionstock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mg.njaka.gestionstock.utils.Constants.APP_ROOT;

public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + "/entreprise/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value = APP_ROOT + "/entreprise/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/entreprise/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprise/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);

}
