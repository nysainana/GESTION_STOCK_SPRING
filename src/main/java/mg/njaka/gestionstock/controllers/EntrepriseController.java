package mg.njaka.gestionstock.controllers;

import mg.njaka.gestionstock.controllers.api.EntrepriseApi;
import mg.njaka.gestionstock.dto.EntrepriseDto;
import mg.njaka.gestionstock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(
            EntrepriseService entrepriseService
    ) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return entrepriseService.delete(id);
    }

}
