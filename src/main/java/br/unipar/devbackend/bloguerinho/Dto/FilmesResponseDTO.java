package br.unipar.devbackend.bloguerinho.Dto;


import lombok.Data;

import java.util.List;

@Data
public class FilmesResponseDTO {
    private int page;
    private List<FilmesDTO> results;
    private int total_pages;
    private int total_results;
}
