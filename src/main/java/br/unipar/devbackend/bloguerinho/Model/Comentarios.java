package br.unipar.devbackend.bloguerinho.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // vai gerar automático o id (problema do banco de dados)
    private Long id;

    @Column(name = "Comentario")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "postID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @Column(name = "CreatedAt")
    private LocalDateTime CreatedAt ;

    public Comentarios(Long id, String comentario, Post post, Usuario usuario, LocalDateTime createdAt) {
        this.id = id;
        this.comentario = comentario;
        this.post = post;
        this.usuario = usuario;
        CreatedAt = createdAt;
    }

    public Comentarios(Post post, Usuario usuario) {
        this.post = post;
        this.usuario = usuario;
        this.CreatedAt = LocalDateTime.now();
    }

    public Comentarios(){};

    public Long getId() {
        return id;
    }

    public String getComentario() {
        return comentario;
    }

    public Post getPost() {
        return post;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
