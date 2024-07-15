package mg.njaka.gestionstock.controllers;

import mg.njaka.gestionstock.controllers.api.ClientApi;
import mg.njaka.gestionstock.dto.ClientDto;
import mg.njaka.gestionstock.services.ClientService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    public ClientController(
            ClientService clientService
    ){
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return clientService.delete(id);
    }

}
