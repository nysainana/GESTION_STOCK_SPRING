package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto save(RoleDto dto);

    RoleDto findById(Integer id);

    List<RoleDto> findAll();

    boolean delete(Integer id);

}
