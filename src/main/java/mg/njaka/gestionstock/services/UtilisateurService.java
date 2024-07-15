package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Integer id);

    List<UtilisateurDto> findAll();

    boolean delete(Integer id);

    UtilisateurDto findUserByEmail(String email);
}
