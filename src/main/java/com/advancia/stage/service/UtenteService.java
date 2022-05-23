package com.advancia.stage.service;


import com.advancia.stage.dao.UtenteDAO;
import com.advancia.stage.dto.UtenteDTO;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.model.Utente;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


@Path("/utente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtenteService {
    @Inject
    UtenteDAO utente_dao;

    private Set<Utente> impasti = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>() ));

    @Transactional
    public void creaUtente(Utente utente){
        System.out.println("Aggiungo utente");
        //em.persist(utente);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UtenteDTO> getUtenti(){
        return utente_dao.ute_list();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UtenteDTO getUtente(@PathParam("id") Long id){
        UtenteDTO utenteFiltro = utente_dao.getUtente(id);
        return utenteFiltro;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Utente addUtente(Utente utente){
        utente_dao.add(utente);
        return utente;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public UtenteDTO update(@PathParam("id") Long id, UtenteDTO utente){
        Utente updUte = new Utente();
        updUte.setId(id);
        updUte.setUsername(utente.getUsername());
        updUte.setPassword(utente.getPassword());
        utente_dao.update(id,updUte);
        System.out.println("Modifica avvenuta");
        return utente;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id){
        utente_dao.delete(id);
        System.out.println("Fine Cancellazione");
    }

}
