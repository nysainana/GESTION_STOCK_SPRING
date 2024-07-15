package mg.njaka.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import mg.njaka.gestionstock.dto.FournisseurDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.EntityNotValidException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.repository.FournisseurRepository;
import mg.njaka.gestionstock.services.FournisseurService;
import mg.njaka.gestionstock.validators.FournisseurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(
            FournisseurRepository fournisseurRepository
    ) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {

        List<String> errors = FournisseurValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("La Fournisseur n'est pas valide", dto);
            throw new EntityNotValidException("La Fournisseur n'est pas valide",
                    ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }

        return FournisseurDto.fromEntity(
                fournisseurRepository.save(
                        FournisseurDto.toEntity(dto)
                )
        );
    }

    @Override
    public FournisseurDto findById(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  null;
        }

        return fournisseurRepository.findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Fournisseur introvable pour l'id : " + id, ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));
    }
    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  false;
        }

        fournisseurRepository.deleteById(id);
        return true;
    }

}
