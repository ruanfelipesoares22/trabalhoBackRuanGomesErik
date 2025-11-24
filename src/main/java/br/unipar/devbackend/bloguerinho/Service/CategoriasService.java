package br.unipar.devbackend.bloguerinho.Service;

import br.unipar.devbackend.bloguerinho.Model.Categorias;
import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Repository.CategoriasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriasService {
    private final CategoriasRepository repository;

    public CategoriasService(CategoriasRepository repository) {
        this.repository = repository;
    }

    public Categorias gravar(Categorias categorias) {
        return repository.save(categorias);
    }

    public List<Categorias> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
