package br.unipar.devbackend.bloguerinho.Dto;

import lombok.Data;

@Data
public class FilmesDTO {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
}
