package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Model.Categorias;
import br.unipar.devbackend.bloguerinho.Service.CategoriasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Categorias")
public class CategoriasController {

    private final CategoriasService categoriasservice;

    public CategoriasController(CategoriasService categoriasservice) {
        this.categoriasservice = categoriasservice;
    }

    @GetMapping("/todos")
    public List<Categorias> findAll() {
        return categoriasservice.findAll();
    }

    @PostMapping
    public ResponseEntity<Categorias> gravar(@RequestBody Categorias categorias) {
        Categorias categoriasNova = categoriasservice.gravar(categorias);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriasNova);
    }
}
