package com.advancia.stage.model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Entity
@Table(name="impasto")
public class Impasto {
    @Id
    @Column(name="id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="nome")
    private String nome;

    @OneToMany(mappedBy = "impasto")
    private List<Pizza> pizza;


    public Impasto() {
    }


    public Impasto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    public Long getId() {
        return id;
    }

    public String getNome(){
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public List<Pizza> getPizza() {
        return pizza;
    }

    public void setPizza(List<Pizza> pizza) {
        this.pizza = pizza;
    }
}
