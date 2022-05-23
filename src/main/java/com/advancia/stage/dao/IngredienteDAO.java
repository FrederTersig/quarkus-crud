package com.advancia.stage.dao;

import com.advancia.stage.dto.IngredienteDTO;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.model.Ingrediente;
import com.advancia.stage.util.ListModel;
import com.advancia.stage.util.SingleModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class IngredienteDAO {

    @Inject
    EntityManager em;

    public List<IngredienteDTO> ing_list(){
        List<IngredienteDTO> list = new ArrayList<IngredienteDTO>();

        Query query = em.createQuery("from Ingrediente");
        List<Ingrediente> res = (ArrayList<Ingrediente>) query.getResultList();
        list = ListModel.transformToIngredienteDTO(res);
        return list;
    }

    public IngredienteDTO getIngrediente(Long id){
        Query query = em.createQuery("from Ingrediente i where i.id = "+id);
        List<Ingrediente> x = query.getResultList();
        return SingleModel.convertToIngredienteDTO(x.get(0));
    }

    public void add(Ingrediente ingrediente){
        em.persist(ingrediente);
        System.out.println("inserimento ingrediente");
        em.flush();
        em.clear();
    }

    public void delete(Long id){
        Ingrediente ingrediente = em.find(Ingrediente.class, id);
        em.remove(ingrediente);
        em.flush();
        em.clear();
    }

    public void update(Long id, Ingrediente ingrediente){
        Ingrediente ingredienteVecchio =em.find(Ingrediente.class, id);
        if(ingrediente.getNome() != null){
            ingredienteVecchio.setNome(ingrediente.getNome());
        }

        em.merge(ingredienteVecchio);
    }
}
