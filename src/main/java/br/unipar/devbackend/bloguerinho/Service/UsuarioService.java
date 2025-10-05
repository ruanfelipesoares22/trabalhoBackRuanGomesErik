package br.unipar.devbackend.bloguerinho.Service;

import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {
    private final UsuariosRepository repository;


    public UsuarioService(UsuariosRepository repository) {
        this.repository = repository;
    }

    public Usuario gravar(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public List<Usuario> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Usuario editar(Usuario usuario) {
        return repository.save(usuario);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
