package mg.njaka.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import mg.njaka.gestionstock.dto.RoleDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.EntityNotValidException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.repository.RoleRepository;
import mg.njaka.gestionstock.services.RoleService;
import mg.njaka.gestionstock.validators.RoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(
            RoleRepository roleRepository
    ) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto save(RoleDto dto) {

        List<String> errors = RoleValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("Le Role n'est pas valide", dto);
            throw new EntityNotValidException("Le Role n'est pas valide",
                    ErrorCodes.ROLE_NOT_VALID, errors);
        }

        return RoleDto.fromEntity(
                roleRepository.save(
                        RoleDto.toEntity(dto)
                )
        );
    }

    @Override
    public RoleDto findById(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  null;
        }

        return roleRepository.findById(id)
                .map(RoleDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Role introvable pour l'id : " + id, ErrorCodes.ROLE_NOT_FOUND
                ));
    }
    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  false;
        }

        roleRepository.deleteById(id);
        return true;
    }

}
