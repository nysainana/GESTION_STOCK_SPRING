package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto dto);

    EntrepriseDto findById(Integer id);

    List<EntrepriseDto> findAll();

    boolean delete(Integer id);

}
