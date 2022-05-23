package com.advancia.stage.util;


import java.util.ArrayList;
import java.util.List;

import com.advancia.stage.dto.*;
import com.advancia.stage.model.*;


public class Manage {


    private static UtenteDTO buildUtenteDTO(Utente utente) {
        UtenteDTO utente_dto = new UtenteDTO();
        utente_dto.setUsername(utente.getUsername());
        utente_dto.setPassword(utente.getPassword());
        utente_dto.setId(utente.getId());
        return utente_dto;
    }

    private static ImpastoDTO buildImpastoDTO(Impasto impasto) {
        ImpastoDTO impasto_dto = new ImpastoDTO();
        impasto_dto.setNome(impasto.getNome());
        impasto_dto.setId(impasto.getId());
        return impasto_dto;
    }


    private static IngredienteDTO buildIngredienteDTO(Ingrediente ingrediente) {
        IngredienteDTO ingrediente_dto = new IngredienteDTO();
        ingrediente_dto.setId(ingrediente.getId());
        ingrediente_dto.setNome(ingrediente.getNome());
        return ingrediente_dto;
    }


    private static List<IngredienteDTO> buildListaIngredienteDTO(List<Ingrediente> listaIngrediente) {
        List<IngredienteDTO> listaIngredienti_dto = new ArrayList<IngredienteDTO>();
        listaIngrediente.forEach(ingrediente -> {
//			IngredienteDTO ingrediente_dto = new IngredienteDTO();
//			ingrediente_dto.setId(Integer.parseInt(ingrediente.getId()));
//			ingrediente_dto.setNome(ingrediente.getNome());
            IngredienteDTO ingrediente_dto = buildIngredienteDTO(ingrediente);
            listaIngredienti_dto.add(ingrediente_dto);
        });
        return listaIngredienti_dto;
    }

    public static List<PizzaDTO> convertPizzaDTOList(List<Pizza> pizzaList, String nullModel){
        List<PizzaDTO> newList = new ArrayList<PizzaDTO>();
        pizzaList.forEach(pizza ->{
            PizzaDTO pizza_dto = new PizzaDTO();
            if(!nullModel.equals("utente")) {
                UtenteDTO utente_dto = buildUtenteDTO(pizza.getUtente());
                pizza_dto.setUtente(utente_dto); // SET UTENTE
            }
            if(!nullModel.equals("impasto")) {
                ImpastoDTO impasto_dto = buildImpastoDTO(pizza.getImpasto());
                pizza_dto.setImpasto(impasto_dto);
            }
            if(!nullModel.equals("ingrediente")) { // QUESTA è una lista
                List<IngredienteDTO> listaIngredienti_dto = buildListaIngredienteDTO(pizza.getIngrediente());
                pizza_dto.setIngredienti(listaIngredienti_dto);
            }
            //Setto i restanti parametri della pizza
            pizza_dto.setNome(pizza.getNome());
            pizza_dto.setId(pizza.getId());

            //aggiungo la pizzaDTO nella lista di pizzaDTO da ritornare.
            newList.add(pizza_dto);
        });
        return newList;
    }

    public static List<ImpastoDTO> convertImpastoDTOList(List<Impasto> impastoList){
        List<ImpastoDTO> newList = new ArrayList<ImpastoDTO>();
        impastoList.forEach(impasto ->{
            ImpastoDTO impasto_dto = buildImpastoDTO(impasto);
            List<PizzaDTO> listaPizza_dto = convertPizzaDTOList(impasto.getPizza(),"impasto");
            impasto_dto.setPizza(listaPizza_dto);
            newList.add(impasto_dto);
        });
        return newList;
    }

    public static List<IngredienteDTO> convertIngredienteDTOList(List<Ingrediente> ingredienteList){
        List<IngredienteDTO> newList = new ArrayList<IngredienteDTO>();
        ingredienteList.forEach(ingrediente ->{
            IngredienteDTO ingrediente_dto = buildIngredienteDTO(ingrediente);
            List<PizzaDTO> ListaPizza_dto = convertPizzaDTOList(ingrediente.getPizza(),"ingrediente");
            ingrediente_dto.setPizza(ListaPizza_dto);
            newList.add(ingrediente_dto);
        });
        return newList;
    }

    public static List<UtenteDTO> convertUtenteDTOList(List<Utente> utenteList){
        List<UtenteDTO> newList = new ArrayList<UtenteDTO>();
        utenteList.forEach(utente ->{
            UtenteDTO utente_dto = buildUtenteDTO(utente);
            List<PizzaDTO> ListaPizza_dto = convertPizzaDTOList(utente.getPizza(),"utente");
            utente_dto.setPizza(ListaPizza_dto);
            newList.add(utente_dto);

        });
        return newList;
    }



}
