package com.advancia.stage.dao;

import com.advancia.stage.dto.IngredienteDTO;
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


        Query query = em.createQuery("from Ingrediente");
        List<Ingrediente> res = (ArrayList<Ingrediente>) query.getResultList();
        if(res != null) {
            return ListModel.transformToIngredienteDTO(res);
        }
        return null;
    }

    public IngredienteDTO getIngrediente(Long id){
        if(id != null && id != 0) {
            Query query = em.createQuery("from Ingrediente i where i.id = " + id);
            Ingrediente x = (Ingrediente) query.getSingleResult();
            if(x != null) {
                return SingleModel.convertToIngredienteDTO(x);
            }
        }
        return null;
    }

    public void add(Ingrediente ingrediente){
        if(ingrediente != null) {
            em.persist(ingrediente);
            System.out.println("inserimento ingrediente");
            em.flush();
            em.clear();
        }
    }

    public void delete(Long id){
        if(id != null && id != 0) {
            Ingrediente ingrediente = em.find(Ingrediente.class, id);
            if(ingrediente != null) {
                em.remove(ingrediente);
                em.flush();
                em.clear();
            }
        }
    }

    public void update(Long id, Ingrediente ingrediente){
        if(id != null && id != 0) {
            if(ingrediente != null) {
                Ingrediente ingredienteVecchio = em.find(Ingrediente.class, id);
                if(ingredienteVecchio != null) {
                    if (ingrediente.getNome() != null) {
                        ingredienteVecchio.setNome(ingrediente.getNome());
                    }

                    em.merge(ingredienteVecchio);
                }
            }
        }
    }
}
