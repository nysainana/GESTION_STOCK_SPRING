package mg.njaka.gestionstock.controllers.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mg.njaka.gestionstock.dto.ArticleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mg.njaka.gestionstock.utils.Constants.APP_ROOT;

@Api(value = APP_ROOT + "/articles", protocols = "http")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/article/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister un articles",
            notes = "Cette methode  permet de modifier et enregister un article",
            response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article est enregistrer / modifier"),
            @ApiResponse(code = 400, message = "L'article nest pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "/article/id={id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un articles",
            notes = "Cette methode  permet de chercher un article par so ID",
            response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete trouver dans le BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'a ete trouver dans le BDD avec l'ID fournis")
    })
    ArticleDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/article/code={code}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un articles",
            notes = "Cette methode  permet de chercher un article par so ID",
            response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete trouver dans le BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'a ete trouver dans le BDD avec le code fournis")
    })
    ArticleDto findByCode(@PathVariable("code") String code);

    @GetMapping(value = APP_ROOT + "/article/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoye la liste des articles",
            notes = "Chercher et renvoyer la liste des articles qui existe dans la BDD",
            responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des articles / liste vide")
    })
    List<ArticleDto> findAll();

    @ApiOperation(value = "Suprimmer un article",
            notes = "Permet desupprimer un article avec l'ID fournis",
            response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article est supprimer")
    })
    @DeleteMapping(value = APP_ROOT + "/article/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);

}
