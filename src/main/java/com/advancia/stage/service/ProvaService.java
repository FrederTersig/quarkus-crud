package com.advancia.stage.service;
//Esempio di application bean che usa Hibernate

import com.advancia.stage.dao.ProvaDAO;
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

@Path("/CANCELLA")
@Produces(MediaType.APPLICATION_JSON)
//@ActivateRequestContext
public class ProvaService {
    //Injetto il mio entity manager
    //@PersistenceUnit("prova")
    //@Inject
    //EntityManager em;
    @Inject
    ProvaDAO prova_dao;



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
    public List<Impasto> getImpasti(){
        //return ProvaDAO.imp_list();
        return prova_dao.imp_list();
    }

/*    @GET
    public List<Impasto> getImpasti(){
        Query query = em.createQuery("from Impasto");
        return (List<Impasto>) query.getResultList();
    }*/


/*    @GET
    public Impasto getImpasto(){
        Query query = em.createQuery("from Impasto where id=3");
        return (Impasto) query.getSingleResult();
    }*/


    @POST
    @Transactional
    public Impasto addImpasto(Impasto impasto){
        //em.persist(impasto);
        return impasto;
        //ritorno la lista aggiornata ?

    }


}
