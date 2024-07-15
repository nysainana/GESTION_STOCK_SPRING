package mg.njaka.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import mg.njaka.gestionstock.dto.UtilisateurDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.EntityNotValidException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.repository.UtilisateurRepository;
import mg.njaka.gestionstock.services.UtilisateurService;
import mg.njaka.gestionstock.validators.UtilisateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(
            UtilisateurRepository utilisateurRepository
    ) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {

        List<String> errors = UtilisateurValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("L'Utilisateur n'est pas valide", dto);
            throw new EntityNotValidException("L'Utilisateur n'est pas valide",
                    ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }

        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(
                        UtilisateurDto.toEntity(dto)
                )
        );
    }

    @Override
    public UtilisateurDto findById(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  null;
        }

        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Utilisateur introvable pour l'id : " + id, ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }
    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  false;
        }

        utilisateurRepository.deleteById(id);
        return true;
    }

    @Override
    public UtilisateurDto findUserByEmail(String email) {

        if(email == null){
            log.error("L'email est null");
            return  null;
        }

        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Utilisateur introvable pour l'email : " + email, ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

}
