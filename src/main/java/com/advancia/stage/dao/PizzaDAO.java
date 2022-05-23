package com.advancia.stage.dao;

import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.model.Pizza;
import com.advancia.stage.util.ListModel;
import com.advancia.stage.util.SingleModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PizzaDAO {

    @Inject
    EntityManager em;

    public List<PizzaDTO> pizza_list(){
        List<PizzaDTO> list = new ArrayList<PizzaDTO>();

        Query query = em.createQuery("from Pizza");
        List<Pizza> res = (ArrayList<Pizza>) query.getResultList();
        list = ListModel.transformToPizzaDTO(res);
        return list;
    }

    public PizzaDTO getPizza(Long id){
        Query query = em.createQuery("from Pizza p where p.id = "+id);
        List<Pizza> x = query.getResultList();
        PizzaDTO pizzaFiltro = SingleModel.convertToPizzaDTO(x.get(0));
        return pizzaFiltro;
    }

    public void add(Pizza pizza){
        em.persist(pizza);
        System.out.println("Fine inserimento di una nuova pizza");
        em.flush();
        em.clear();
    }

    public void delete(Long id){
        Pizza pizza = em.find(Pizza.class, id);
        em.remove(pizza);
        em.flush();
        em.clear();
    }

    public void update(Long id, Pizza pizza){
        Pizza pizzaVecchia = em.find(Pizza.class, id);
        if(pizza.getNome() != null){
            pizzaVecchia.setNome(pizza.getNome());
        }

        em.merge(pizzaVecchia);
    }

}
