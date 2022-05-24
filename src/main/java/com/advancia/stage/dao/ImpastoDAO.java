package com.advancia.stage.dao;

import com.advancia.stage.dto.ImpastoDTO;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.util.ListModel;
import com.advancia.stage.util.SingleModel;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ImpastoDAO {

    @Inject
    EntityManager em;

    public List<ImpastoDTO> imp_list(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Impasto> cr = cb.createQuery(Impasto.class);
        Root<Impasto> root = cr.from(Impasto.class);
        cr.select(root);

        Query query = em.createQuery(cr);
        List<Impasto> res = (ArrayList<Impasto>) query.getResultList();
        return ListModel.transformToImpastoDTO(res);
    }


    public ImpastoDTO getImpasto(Long id){
        Query query = em.createQuery("from Impasto i where i.id = "+id);
        Impasto x = (Impasto) query.getSingleResult();
        if(x!=null){
            return SingleModel.convertToImpastoDTO(x);
        }
        return null;
    }
    public void add(Impasto impasto){
        if(impasto != null) {
            em.persist(impasto);
            System.out.println("Fine dell'inserimento di un nuovo impasto");
            em.flush();
            em.clear();
        }
    }

    public void delete(Long id){
        if(id != null && id != 0) {
            Impasto impasto = em.find(Impasto.class, id);
            if(impasto != null) {
                em.remove(impasto);
                em.flush();
                em.clear();
            }
        }
    }

    public void update(Long id, Impasto impasto){
        if(id != null && id != 0) {
            if(impasto != null) {
                Impasto impastoVecchio = em.find(Impasto.class, id);
                if(impastoVecchio != null) {
                    if (impasto.getNome() != null) {
                        impastoVecchio.setNome(impasto.getNome());
                    }
                    em.merge(impastoVecchio);
                }
            }
        }
    }


}
