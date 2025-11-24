package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Dto.FilmesDTO;
import br.unipar.devbackend.bloguerinho.Model.Filmes;
import br.unipar.devbackend.bloguerinho.Repository.FilmesRepository;
import br.unipar.devbackend.bloguerinho.Service.FilmesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/filmes")
public class FilmesController {

    @Autowired
    private FilmesService filmesService;

    @Operation(
            summary = "Listar filmes populares",
            description = "Retorna filmes diretamente da API do TMDb."
    )
    @GetMapping
    public List<FilmesDTO> listarFilmes() {
        return filmesService.buscarFilmesPopulares();
    }
}