package com.advancia.stage.dto;

import java.util.List;

public class IngredienteDTO {
    Long id;
    String nome;
    List<PizzaDTO> pizza;

    public IngredienteDTO() {
    }

    public IngredienteDTO(Long id, String nome, List<PizzaDTO> pizza) {
        this.id = id;
        this.nome = nome;
        this.pizza = pizza;
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

    public List<PizzaDTO> getPizza() {
        return pizza;
    }

    public void setPizza(List<PizzaDTO> pizza) {
        this.pizza = pizza;
    }
}
