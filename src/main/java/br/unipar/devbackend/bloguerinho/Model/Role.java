package br.unipar.devbackend.bloguerinho.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PermissoesEnum permissao;

    public Role() {}

    public Role(Long id, PermissoesEnum permissao) {
        this.id = id;
        this.permissao = permissao;
    }
}
