package br.unipar.devbackend.bloguerinho.Service;

import br.unipar.devbackend.bloguerinho.Dto.FilmesDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmesSchedule {

    private final FilmesService filmesService;

    public FilmesSchedule(FilmesService filmesService) {
        this.filmesService = filmesService;
    }

    /**
     * Executa a cada 1 hora (3600000 ms)
     */
    @Scheduled(fixedRate = 3600000)
    public void atualizarFilmes() {
        System.out.println("Iniciando atualização de filmes...");
        try {
            List<FilmesDTO> filmes = filmesService.buscarFilmesPopulares();
            System.out.println("Foram obtidos " + filmes.size() + " filmes populares.");
            // Aqui você poderia salvar no banco, se quiser
        } catch (Exception e) {
            System.err.println("Erro ao atualizar filmes: " + e.getMessage());
        }
        System.out.println("Atualização de filmes finalizada!");
    }
}
