package br.unipar.devbackend.bloguerinho.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Curtidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // vai gerar automático o id (problema do banco de dados)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postID") // cria a FK para a tabela Usuario
    private Post post;

    @ManyToOne
    @JoinColumn(name = "usuarioId") // cria a FK para a tabela Usuario
    private Usuario usuario;

    @Column(name = "CreatedAt")
    private LocalDateTime CreatedAt ;

    public Curtidas(Long id, Post post, Usuario usuario, LocalDateTime createdAt) {
        this.id = id;
        this.post = post;
        this.usuario = usuario;
        CreatedAt = createdAt;
    }

    public Curtidas(Post post, Usuario usuario) {
        this.post = post;
        this.usuario = usuario;
        this.CreatedAt = LocalDateTime.now();
    }
    public Curtidas() {};

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }
}
