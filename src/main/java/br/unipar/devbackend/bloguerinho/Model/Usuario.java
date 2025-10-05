package br.unipar.devbackend.bloguerinho.Model;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // vai gerar automático o id (problema do banco de dados)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "username")
    private String username;

    public Usuario(Long id, String nome, String username) {
        this.id = id;
        this.nome = nome;
        this.username = username;
    }

    public Usuario() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
