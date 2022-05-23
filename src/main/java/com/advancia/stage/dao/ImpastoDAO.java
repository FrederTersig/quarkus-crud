package com.advancia.stage.dao;

import com.advancia.stage.dto.ImpastoDTO;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.util.ListModel;
import com.advancia.stage.util.SingleModel;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ImpastoDAO {

    @Inject
    EntityManager em;

    public List<ImpastoDTO> imp_list(){
        List<ImpastoDTO> list = new ArrayList<ImpastoDTO>();

        Query query = em.createQuery("from Impasto");
        List<Impasto> res = (ArrayList<Impasto>) query.getResultList();
        list = ListModel.transformToImpastoDTO(res);
        return list;
    }


    public ImpastoDTO getImpasto(Long id){
        Query query = em.createQuery("from Impasto i where i.id = "+id);
        List<Impasto> x = query.getResultList();
        System.out.println(x);
        ImpastoDTO impastoFiltro = SingleModel.convertToImpastoDTO(x.get(0));
        return impastoFiltro;
        //return x.get(0);
    }
    public void add(Impasto impasto){
        em.persist(impasto);
        System.out.println("Fine dell'inserimento di un nuovo impasto");
        em.flush();
        em.clear();
    }

    public void delete(Long id){
        Impasto impasto = em.find(Impasto.class, id);
        em.remove(impasto);
        em.flush();
        em.clear();
    }

    public void update(Long id, Impasto impasto){
        Impasto impastoVecchio = em.find(Impasto.class, id);
        if(impasto.getNome() != null){
            impastoVecchio.setNome(impasto.getNome());
        }
        em.merge(impastoVecchio);
    }


/*
    public List<ImpastoDTO> impastiList(){
        List<ImpastoDTO> list = new ArrayList<ImpastoDTO>();

        Query query = em.createQuery("from Impasto");
        List<Impasto> res = (ArrayList<Impasto>) query.getResultList();
        list = ListModel.transformToImpastoDTO(res);
        return list;
    }
*/





}
