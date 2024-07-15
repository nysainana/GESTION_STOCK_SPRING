package mg.njaka.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import mg.njaka.gestionstock.dto.CommandeClientDto;
import mg.njaka.gestionstock.dto.LigneCommandeClientDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.EntityNotValidException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.model.Article;
import mg.njaka.gestionstock.model.Client;
import mg.njaka.gestionstock.model.CommandeClient;
import mg.njaka.gestionstock.model.LigneCommandeClient;
import mg.njaka.gestionstock.repository.ArticleRepository;
import mg.njaka.gestionstock.repository.ClientRepository;
import mg.njaka.gestionstock.repository.CommandeClientRepository;
import mg.njaka.gestionstock.repository.LigneCommandeClientRepository;
import mg.njaka.gestionstock.services.CommandeClientService;
import mg.njaka.gestionstock.validators.CommandeClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(
            CommandeClientRepository commandeClientRepository,
            ClientRepository clientRepository,
            ArticleRepository articleRepository,
            LigneCommandeClientRepository ligneCommandeClientRepository
    ) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {

        List<String> errors = CommandeClientValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("La commande client n'est pas valide");
            throw new EntityNotValidException("La commande client n'est pas valide",
                    ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if(!client.isPresent()){
            log.warn("Le client avec ID {} n'est pas dans la BDD", dto.getClient().getId());
            throw new EntityNotFoundException("Le client avec ID " + dto.getClient().getId()
                    + " n'est pas dans la BDD", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();
        if(dto.getLigneCommandeClients() != null){
            dto.getLigneCommandeClients().forEach(lgnCmdClt -> {
                if(lgnCmdClt.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(lgnCmdClt.getArticle().getId());
                    if (!article.isPresent()) {
                        articleErrors.add("L'article avec ID " + lgnCmdClt.getArticle().getId() + " n'est pas dans BDD");
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

        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if(dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(lcc -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(lcc);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  null;
        }

        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Commande client introvable pour l'id : " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {

        if(id == null){
            log.error("L'ID est null");
            return  false;
        }

        commandeClientRepository.deleteById(id);

        return true;
    }

}
