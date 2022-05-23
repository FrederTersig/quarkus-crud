package com.advancia.stage.dto;

import java.util.List;

public class UtenteDTO {

    Long id;
    String username;
    String password;
    List<PizzaDTO> pizza;

    public UtenteDTO() {}

    public UtenteDTO(Long id, String username, String password, List<PizzaDTO> pizza) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.pizza = pizza;
    }

    public UtenteDTO(Long id, String username){
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PizzaDTO> getPizza() {
        return pizza;
    }

    public void setPizza(List<PizzaDTO> pizza) {
        this.pizza = pizza;
    }
}
