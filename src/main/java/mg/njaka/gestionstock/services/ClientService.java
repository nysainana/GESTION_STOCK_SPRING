package mg.njaka.gestionstock.services;

import mg.njaka.gestionstock.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto dto);

    ClientDto findById(Integer id);

    List<ClientDto> findAll();

    boolean delete(Integer id);

}
