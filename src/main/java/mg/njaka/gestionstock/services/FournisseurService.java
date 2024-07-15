package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Integer id);

    List<FournisseurDto> findAll();

    boolean delete(Integer id);

}
