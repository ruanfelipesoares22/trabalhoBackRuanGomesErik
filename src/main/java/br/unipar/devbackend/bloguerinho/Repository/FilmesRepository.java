package br.unipar.devbackend.bloguerinho.Repository;

import br.unipar.devbackend.bloguerinho.Model.Filmes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmesRepository extends JpaRepository<Filmes, Long> {
}
