package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto dto);

    CommandeClientDto findById(Integer id);

    List<CommandeClientDto> findAll();

    boolean delete(Integer id);

}
