package com.advancia.stage.service;
//Esempio di application bean che usa Hibernate

import com.advancia.stage.dao.ImpastoDAO;
import com.advancia.stage.dao.ProvaDAO;
import com.advancia.stage.dto.ImpastoDTO;
import com.advancia.stage.model.Impasto;
import io.quarkus.hibernate.orm.PersistenceUnit;
//import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;


import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
//import java.util.TimeZone;
import com.advancia.stage.dao.ProvaDAO;

@Path("/impasto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@ActivateRequestContext
public class ImpastoService {

    @Inject
    ImpastoDAO impasto_dao;


    //private List<Impasto> impasti = Collections.synchronizedList(new ArrayList<>())
    private Set<Impasto> impasti = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>() ));

    //Il bean method è transactional e l'EM enlista e flusha al commit
    @Transactional
    public void creaImpasto(String nome){
        Impasto impasto = new Impasto();
        impasto.setNome(nome);
        //em.persist(impasto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ImpastoDTO> getImpasti(){
        return impasto_dao.imp_list();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ImpastoDTO getImpasto( @PathParam("id") Long id){
        System.out.println("id ==== " + id);
        ImpastoDTO x = impasto_dao.getImpasto(id);
        System.out.println(x);
        return x;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Impasto add(Impasto impasto){
        impasto_dao.add(impasto);
        return impasto;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public ImpastoDTO update(@PathParam("id") Long id, ImpastoDTO impasto){
        Impasto updImp = new Impasto();
        updImp.setId(id);
        updImp.setNome(impasto.getNome());
        impasto_dao.update(id,updImp);
        System.out.println("Modifica avvenuta");
        return impasto;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id){
        System.out.println("cancella?");
        impasto_dao.delete(id);
        System.out.println("Fine cancellazione");
    }




}
