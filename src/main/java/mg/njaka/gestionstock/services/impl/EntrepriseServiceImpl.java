package mg.njaka.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import mg.njaka.gestionstock.dto.EntrepriseDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.EntityNotValidException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.repository.EntrepriseRepository;
import mg.njaka.gestionstock.services.EntrepriseService;
import mg.njaka.gestionstock.validators.EntrepriseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(
            EntrepriseRepository entrepriseRepository
    ) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {

        List<String> errors = EntrepriseValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("L'Entreprise n'est pas valide", dto);
            throw new EntityNotValidException("L'Entreprise n'est pas valide",
                    ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }

        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(
                        EntrepriseDto.toEntity(dto)
                )
        );
    }

    @Override
    public EntrepriseDto findById(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  null;
        }

        return entrepriseRepository.findById(id)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Entreprise introvable pour l'id : " + id, ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  false;
        }

        entrepriseRepository.deleteById(id);
        return true;
    }
}
