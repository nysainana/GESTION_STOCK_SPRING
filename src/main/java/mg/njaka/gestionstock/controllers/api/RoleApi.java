package mg.njaka.gestionstock.controllers.api;

import mg.njaka.gestionstock.dto.RoleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mg.njaka.gestionstock.utils.Constants.APP_ROOT;

public interface RoleApi {

    @PostMapping(value = APP_ROOT + "/role/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    RoleDto save(@RequestBody RoleDto dto);

    @GetMapping(value = APP_ROOT + "/role/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    RoleDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/role/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<RoleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/role/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);
}
