package br.unipar.devbackend.bloguerinho.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissoesEnum {
    SUPER(1,"SUPER"),
    COMUM(2, "COMUM");


    private final Integer id;
    private final String descricao;
}
