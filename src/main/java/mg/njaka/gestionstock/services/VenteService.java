package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.VenteDto;

import java.util.List;

public interface VenteService {

    VenteDto save(VenteDto dto);

    VenteDto findById(Integer id);

    List<VenteDto> findAll();

    boolean delete(Integer id);

}
