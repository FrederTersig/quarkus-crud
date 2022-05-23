package com.advancia.stage.dao;

import com.advancia.stage.model.Impasto;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProvaDAO {
    @Inject
    EntityManager em;


    public List<Impasto> imp_list(){

        Query query = em.createQuery("from Impasto");
        List<Impasto> res = (ArrayList<Impasto>) query.getResultList();

        return res;

    }

}
