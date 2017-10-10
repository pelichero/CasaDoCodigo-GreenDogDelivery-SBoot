package edu.fiap.estudos.casadocodigo.greendogdelivery.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public	class Propriedade {
    @Id
    private String nome;
    private String valor;
    private String descricao;
    private String categoria;
    private String subcategoria;
}