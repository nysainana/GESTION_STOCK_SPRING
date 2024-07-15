package mg.njaka.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import mg.njaka.gestionstock.dto.CommandeFournisseurDto;
import mg.njaka.gestionstock.dto.LigneCommandeFournisseurDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.EntityNotValidException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.model.Article;
import mg.njaka.gestionstock.model.CommandeFournisseur;
import mg.njaka.gestionstock.model.Fournisseur;
import mg.njaka.gestionstock.model.LigneCommandeFournisseur;
import mg.njaka.gestionstock.repository.ArticleRepository;
import mg.njaka.gestionstock.repository.CommandeFournisseurRepository;
import mg.njaka.gestionstock.repository.FournisseurRepository;
import mg.njaka.gestionstock.repository.LigneCommandeFournisseurRepository;
import mg.njaka.gestionstock.services.CommandeFournisseurService;
import mg.njaka.gestionstock.validators.CommandeFournisseurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(
            CommandeFournisseurRepository commandeFournisseurRepository,
            FournisseurRepository fournisseurRepository,
            ArticleRepository articleRepository,
            LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository
    ) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {

        List<String> errors = CommandeFournisseurValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("La commande fournisseur n'est pas valide");
            throw new EntityNotValidException("La commande fournisseur n'est pas valide",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseurDto().getId());
        if(!fournisseur.isPresent()){
            log.warn("La fournisseur avec ID {} n'est pas dans la BDD", dto.getFournisseurDto().getId());
            throw new EntityNotFoundException("La fournisseur avec ID " + dto.getFournisseurDto().getId()
                    + " n'est pas dans la BDD", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }


        List<String> articleErrors = new ArrayList<>();
        if(dto.getLigneCommandeFournisseur() != null){
            dto.getLigneCommandeFournisseur().forEach(lgnCmdfr -> {
                if(lgnCmdfr.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(lgnCmdfr.getArticle().getId());
                    if (!article.isPresent()) {
                        articleErrors.add("L'article avec ID " + lgnCmdfr.getArticle().getId() + " n'est pas dans BDD");
                    }
                }
                else{
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });
        }

        if(!articleErrors.isEmpty()){
            throw new EntityNotValidException("Articles n'existe pas dans BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }


        CommandeFournisseur savedCmdFr = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if(dto.getLigneCommandeFournisseur() != null) {
            dto.getLigneCommandeFournisseur().forEach(lcc -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(lcc);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFr);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCmdFr);

    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  null;
        }

        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Commande fournisseur introvable pour l'id : " + id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  false;
        }

        commandeFournisseurRepository.deleteById(id);

        return true;
    }

}
