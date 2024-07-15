package mg.njaka.gestionstock.controllers.api;

import mg.njaka.gestionstock.dto.VenteDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mg.njaka.gestionstock.utils.Constants.APP_ROOT;

public interface VenteApi {

    @PostMapping(value = APP_ROOT + "/vente/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    VenteDto save(@RequestBody VenteDto dto);

    @GetMapping(value = APP_ROOT + "/vente/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    VenteDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/vente/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<VenteDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/vente/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);
}
