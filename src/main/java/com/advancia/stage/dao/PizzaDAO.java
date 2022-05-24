package com.advancia.stage.dao;

import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.model.Pizza;
import com.advancia.stage.util.ListModel;
import com.advancia.stage.util.SingleModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PizzaDAO implements PanacheRepository<Pizza> {

    @Inject
    EntityManager em;

    public List<PizzaDTO> pizza_list(){
        List<PizzaDTO> list = new ArrayList<PizzaDTO>();

        Query query = em.createQuery("from Pizza");
        List<Pizza> res = (ArrayList<Pizza>) query.getResultList();
        if(res != null) {
            list = ListModel.transformToPizzaDTO(res);
            return list;
        }else{
            return null;
        }
    }

    public PizzaDTO getPizza(Long id){
        Query query = em.createQuery("from Pizza p where p.id = "+id);
        Pizza x = (Pizza) query.getSingleResult();
        if(x != null) {
            return SingleModel.convertToPizzaDTO(x);
        }else{
            return null;
        }
    }


    public PizzaDTO findByNome(String nome) {

        Pizza result = find("nome", nome).firstResult();
        if (result != null){
            return SingleModel.convertToPizzaDTO(result);
        }else{
            return null;
        }
    }

    public void add(Pizza pizza){
        if(pizza!=null) {
            em.persist(pizza);
            System.out.println("Fine inserimento di una nuova pizza");
            em.flush();
            em.clear();
        }

    }

    public void delete(Long id){
        if(id != null && id != 0) {
            Pizza pizza = em.find(Pizza.class, id);
            if(pizza != null){
                em.remove(pizza);
                em.flush();
                em.clear();
            }

        }
    }

    public void update(Long id, Pizza pizza){
        if(id != 0 && id != null && pizza != null) {
            Pizza pizzaVecchia = em.find(Pizza.class, id);
            if(pizzaVecchia != null) {
                if (pizza.getNome() != null) {
                    pizzaVecchia.setNome(pizza.getNome());
                }
                em.merge(pizzaVecchia);
            }
        }

    }

}
