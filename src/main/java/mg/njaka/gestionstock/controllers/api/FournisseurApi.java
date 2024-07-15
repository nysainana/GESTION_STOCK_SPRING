package mg.njaka.gestionstock.controllers.api;

import mg.njaka.gestionstock.dto.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mg.njaka.gestionstock.utils.Constants.APP_ROOT;

public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseur/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value = APP_ROOT + "/fournisseur/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseur/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseur/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);
}
