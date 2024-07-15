package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto dto);

    CommandeFournisseurDto findById(Integer id);

    List<CommandeFournisseurDto> findAll();

    boolean delete(Integer id);

}
