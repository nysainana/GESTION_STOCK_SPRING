package mg.njaka.gestionstock.controllers;

import mg.njaka.gestionstock.controllers.api.RoleApi;
import mg.njaka.gestionstock.dto.RoleDto;
import mg.njaka.gestionstock.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController implements RoleApi {

    private RoleService roleService;

    @Autowired
    public RoleController(
            RoleService roleService
    ) {
        this.roleService = roleService;
    }

    @Override
    public RoleDto save(RoleDto dto) {
        return roleService.save(dto);
    }

    @Override
    public RoleDto findById(Integer id) {
        return roleService.findById(id);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return roleService.delete(id);
    }

}
