package br.unipar.devbackend.bloguerinho.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conteudo")
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "autorId")
    private Usuario autor;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "likescount")
    private Long likesCount = 0L;

    public Post(Long id, Usuario autor, String conteudo, LocalDateTime createdAt, Long likesCount) {
        this.id = id;
        this.autor = autor;
        this.conteudo = conteudo;
        this.createdAt = createdAt;
        this.likesCount = likesCount;
    }

    public Post(){

    }

    public Long getId() {
        return id;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }


}
