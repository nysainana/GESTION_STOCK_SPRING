package mg.njaka.gestionstock.controllers;

import mg.njaka.gestionstock.controllers.api.VenteApi;
import mg.njaka.gestionstock.dto.VenteDto;
import mg.njaka.gestionstock.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements VenteApi {

    private VenteService venteService;

    @Autowired
    public VenteController(
            VenteService venteService
    ) {
        this.venteService = venteService;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        return venteService.save(dto);
    }

    @Override
    public VenteDto findById(Integer id) {
        return venteService.findById(id);
    }

    @Override
    public List<VenteDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return venteService.delete(id);
    }


}
