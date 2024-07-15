package mg.njaka.gestionstock.controllers.api;

import mg.njaka.gestionstock.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mg.njaka.gestionstock.utils.Constants.APP_ROOT;

public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateur/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value = APP_ROOT + "/utilisateur/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateur/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateur/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);

}
