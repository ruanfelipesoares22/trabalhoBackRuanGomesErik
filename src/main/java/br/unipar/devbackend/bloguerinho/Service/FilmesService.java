package br.unipar.devbackend.bloguerinho.Service;

import br.unipar.devbackend.bloguerinho.Dto.FilmesDTO;
import br.unipar.devbackend.bloguerinho.Dto.FilmesResponseDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FilmesService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${tmdb.api.key}")
    private String apiKey;

    public List<FilmesDTO> buscarFilmesPopulares() {

        String url = "https://api.themoviedb.org/3/movie/popular?api_key="
                + apiKey + "&language=pt-BR";

        FilmesResponseDTO response =
                restTemplate.getForObject(url, FilmesResponseDTO.class);

        return response != null ? response.getResults() : List.of();
    }
}