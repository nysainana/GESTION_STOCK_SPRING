package mg.njaka.gestionstock.controllers;

import mg.njaka.gestionstock.controllers.api.FournisseurApi;
import mg.njaka.gestionstock.dto.FournisseurDto;
import mg.njaka.gestionstock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FournisseurController implements FournisseurApi {

    private FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(
            FournisseurService fournisseurService
    ) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return fournisseurService.delete(id);
    }

}
