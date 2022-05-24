package com.advancia.stage.service;


import com.advancia.stage.dao.PizzaDAO;
import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.model.Pizza;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/pizza")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PizzaService {

    @Inject
    PizzaDAO pizza_dao;

    @Transactional
    public void creaPizza(Pizza pizza){
        System.out.println("crea pizza");
        //em.persist(pizza);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PizzaDTO> getPizze(){
        return pizza_dao.pizza_list();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PizzaDTO getPizza(@PathParam("id") Long id){
        PizzaDTO x = pizza_dao.getPizza(id);
        return x;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Pizza addPizza(Pizza pizza){
        System.out.println(pizza);
        pizza_dao.add(pizza);
        return pizza;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public PizzaDTO update(@PathParam("id") Long id, PizzaDTO pizza){
        Pizza updPiz = new Pizza();
        updPiz.setId(id);
        updPiz.setNome(pizza.getNome());
        pizza_dao.update(id,updPiz);
        System.out.println("modifica avvenuta");
        return pizza;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id){
        pizza_dao.delete(id);
        System.out.println("Fine cancellazione");
    }

}
