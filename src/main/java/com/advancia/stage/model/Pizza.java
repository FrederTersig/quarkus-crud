package com.advancia.stage.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", columnDefinition = "serial")
    private long id;
    @Column(name="nome")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    //private Long impasto_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "impasto_id")
    private Impasto impasto;

    @ManyToMany(targetEntity = Ingrediente.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "pizza_ingrediente",
    joinColumns = {@JoinColumn(name = "pizza_id", referencedColumnName="id")},
    inverseJoinColumns = {@JoinColumn(name =
            "ingrediente_id", referencedColumnName="id")} )
    private List<Ingrediente> ingrediente;


    public Pizza() {
    }

    public Pizza(long id, String nome) {
        this.id = id;
        this.nome = nome;

    }


    public long getId() {
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

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Impasto getImpasto() {
        return impasto;
    }

    public void setImpasto(Impasto impasto) {
        this.impasto = impasto;
    }

    public List<Ingrediente> getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(List<Ingrediente> ingrediente) {
        this.ingrediente = ingrediente;
    }
}
