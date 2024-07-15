package mg.njaka.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import mg.njaka.gestionstock.dto.LigneVenteDto;
import mg.njaka.gestionstock.dto.VenteDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.EntityNotValidException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.model.Article;
import mg.njaka.gestionstock.model.LigneVente;
import mg.njaka.gestionstock.model.Vente;
import mg.njaka.gestionstock.repository.ArticleRepository;
import mg.njaka.gestionstock.repository.LigneVenteRepository;
import mg.njaka.gestionstock.repository.VenteRepository;
import mg.njaka.gestionstock.services.VenteService;
import mg.njaka.gestionstock.validators.VenteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private VenteRepository venteRepository;
    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VenteServiceImpl(
            VenteRepository venteRepository,
            ArticleRepository articleRepository,
            LigneVenteRepository ligneVenteRepository
    ) {
        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VenteDto save(VenteDto dto) {

        List<String> errors = VenteValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("Le vente n'est pas valide", dto);
            throw new EntityNotValidException("Le vente n'est pas valide",
                    ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articlesErrors = new ArrayList<>();

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());

            if(article.isPresent()){
                articlesErrors.add("L'article " + ligneVenteDto.getArticle().getId() + " n'existe pas" );
            }
        });

        if(!articlesErrors.isEmpty()){
            log.error("Un ou plusieur article n'existe pas", articlesErrors);
            throw new EntityNotValidException("Un ou plusieur article n'existe pas",
                    ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Vente venteSaved = venteRepository.save(VenteDto.toEntity(dto));

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(venteSaved);
            ligneVenteRepository.save(ligneVente);
        });

        return VenteDto.fromEntity(venteSaved);
    }

    @Override
    public VenteDto findById(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  null;
        }

        return venteRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Vente introvable pour l'id : " + id, ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  false;
        }

        venteRepository.deleteById(id);
        return true;
    }

}
