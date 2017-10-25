package edu.fiap.estudos.casadocodigo.greendogdelivery.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Item {

    public Item(){}

    public Item(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min=2, max=30,
    message="O tamanho do nome deve ser entre {min} e {max} caracteres")
    private String nome;

    @NotNull
    @Min(value=20,message="O valor mínimo deve ser {value} reais")
    private Double preco;
}