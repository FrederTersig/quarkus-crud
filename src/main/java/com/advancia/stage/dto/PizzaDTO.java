package com.advancia.stage.dto;

import java.util.List;

public class PizzaDTO {
    Long id;
    String nome;
    List<IngredienteDTO> ingredienti;

    UtenteDTO utente;
    ImpastoDTO impasto;

    public PizzaDTO() {}

    public PizzaDTO(Long id, String nome, List<IngredienteDTO> ingredienti) {
        this.id = id;
        this.nome = nome;
        this.ingredienti = ingredienti;
    }

    public PizzaDTO(Long id, UtenteDTO utente, String nome) {
        this.utente = utente;
        this.id = id;
        this.nome = nome;
    }

    public PizzaDTO(UtenteDTO utente, Long id, String nome, List<IngredienteDTO> ingredienti) {
        this.utente = utente;
        this.id = id;
        this.nome = nome;
        this.ingredienti = ingredienti;
    }

    public PizzaDTO(Long id) {
        this.id = id;
    }


    public Long getId() {
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

    public List<IngredienteDTO> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<IngredienteDTO> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public UtenteDTO getUtente() {
        return utente;
    }

    public void setUtente(UtenteDTO utente) {
        this.utente = utente;
    }

    public ImpastoDTO getImpasto() {
        return impasto;
    }

    public void setImpasto(ImpastoDTO impasto) {
        this.impasto = impasto;
    }
}
