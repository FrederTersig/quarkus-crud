package com.advancia.stage.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", columnDefinition = "serial")
    private long id;
    @Column(name="nome")
    private String nome;

    @ManyToMany(targetEntity = Pizza.class,mappedBy = "ingrediente")
    private List<Pizza> pizza;

    public Ingrediente() {
    }

    public Ingrediente(long id, String nome) {
        this.id = id;
        this.nome = nome;

    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public List<Pizza> getPizza() {
        return pizza;
    }

    public void setPizza(List<Pizza> pizza) {
        this.pizza = pizza;
    }
}
