package com.advancia.stage.service;

import com.advancia.stage.dao.IngredienteDAO;
import com.advancia.stage.dto.IngredienteDTO;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.model.Ingrediente;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Path("/ingrediente")
@Produces(MediaType.APPLICATION_JSON)
public class IngredienteService {
    @Inject
    IngredienteDAO ingrediente_dao;

    private Set<Ingrediente> ingredienti = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>() ));

    @Transactional
    public void creaIngrediente(Ingrediente ingrediente){
        System.out.println("crea ingrediente");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IngredienteDTO> getIngredienti(){
        System.out.println("Fin qui ci arrivo");
        //System.out.println(ingrediente_dao.ing_list());
        System.out.println("---------------------");
        return ingrediente_dao.ing_list();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IngredienteDTO getIngrediente( @PathParam("id") Long id){
        IngredienteDTO x = ingrediente_dao.getIngrediente(id);
        System.out.println(x);
        System.out.println("-----------------------------");
        return x;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Ingrediente add(Ingrediente ingrediente){
        ingrediente_dao.add(ingrediente);
        return ingrediente;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public IngredienteDTO update(@PathParam("id") Long id, IngredienteDTO ingrediente){
        Ingrediente updIng = new Ingrediente();
        updIng.setId(id);
        updIng.setNome(ingrediente.getNome());
        ingrediente_dao.update(id,updIng);
        System.out.println("modifica avvenuta");
        return ingrediente;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id){
        ingrediente_dao.delete(id);
        System.out.println("cancellazione avvenuta");
    }

}
