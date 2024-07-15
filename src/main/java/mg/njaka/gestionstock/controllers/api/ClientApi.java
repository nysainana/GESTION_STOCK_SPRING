package mg.njaka.gestionstock.controllers.api;

import mg.njaka.gestionstock.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mg.njaka.gestionstock.utils.Constants.APP_ROOT;

public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/client/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/client/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/client/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/client/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);

}
