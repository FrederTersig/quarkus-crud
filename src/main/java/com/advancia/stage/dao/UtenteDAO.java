package com.advancia.stage.dao;


import com.advancia.stage.dto.UtenteDTO;
import com.advancia.stage.model.Utente;
import com.advancia.stage.util.ListModel;
import com.advancia.stage.util.SingleModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UtenteDAO {

    @Inject
    EntityManager em;

    public List<UtenteDTO> ute_list(){
        Query query = em.createQuery("from Utente");
        List<Utente> utenti = (ArrayList<Utente>) query.getResultList();
        if(utenti!=null) {
            return ListModel.transformToUtenteDTO(utenti);
        }
        return null;
    }

    public UtenteDTO getUtente(Long id){
        Query query = em.createQuery("from Utente u where u.id = "+id);
        Utente utenti = (Utente) query.getSingleResult();
        if(utenti!=null) {
            return SingleModel.convertToUtenteDTO(utenti);
        }
        return null;
    }

    public void add(Utente utente){
        if(utente != null) {
            em.persist(utente);
            System.out.println("fine inserimento utente");
            em.flush();
            em.clear();
        }
    }

    public void delete(Long id){
        if(id != null) {
            Utente utente = em.find(Utente.class, id);
            em.remove(utente);
            em.flush();
            em.clear();
        }
    }

    public void update(Long id, Utente utente){
        if(id != null && id != 0) {
            if(utente != null) {
                Utente utenteVecchio = em.find(Utente.class, id);
                if(utenteVecchio != null) {
                    if (utente.getUsername() != null) {
                        utenteVecchio.setUsername(utente.getUsername());
                    }
                    if (utente.getPassword() != null) {
                        utenteVecchio.setPassword(utente.getPassword());
                    }
                    em.merge(utenteVecchio);
                }
            }
        }
    }
}
