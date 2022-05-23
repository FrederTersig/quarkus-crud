package com.advancia.stage.util;

import java.util.ArrayList;
import java.util.List;

import com.advancia.stage.dto.ImpastoDTO;
import com.advancia.stage.dto.IngredienteDTO;
import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.dto.UtenteDTO;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.model.Ingrediente;
import com.advancia.stage.model.Pizza;
import com.advancia.stage.model.Utente;

public class SingleModel {



	private static List<IngredienteDTO> createIngredienteList( List<Ingrediente> lista){
		List<IngredienteDTO> listaTradotta = new ArrayList<IngredienteDTO>();
		lista.forEach(ingrediente -> {
			IngredienteDTO ingredienteMorph = new IngredienteDTO();
			ingredienteMorph.setId(ingrediente.getId());
			ingredienteMorph.setNome(ingrediente.getNome());
			listaTradotta.add(ingredienteMorph);
		});
		return listaTradotta;
	}

	public static IngredienteDTO convertToIngredienteDTO(Ingrediente ingrediente){
		IngredienteDTO ingrediente_dto = new IngredienteDTO();
		ingrediente_dto.setId(ingrediente.getId());
		ingrediente_dto.setNome(ingrediente.getNome());
		List<PizzaDTO> pizzaListDTO = Manage.convertPizzaDTOList(ingrediente.getPizza(), "ingrediente");
		ingrediente_dto.setPizza(pizzaListDTO);
		return ingrediente_dto;
	}

	public static ImpastoDTO convertToImpastoDTO(Impasto impasto){
		ImpastoDTO impasto_dto = new ImpastoDTO();
		impasto_dto.setId(impasto.getId());
		impasto_dto.setNome(impasto.getNome());
		List<PizzaDTO> pizze = new ArrayList<PizzaDTO>();
		impasto.getPizza().forEach(pizza -> {
			/*System.out.println(pizza + " ----------------- <<< ");*/
			PizzaDTO pizzaMorph = new PizzaDTO();
			//Setto l'utente che ha creato la pizza
			UtenteDTO utentePizza = new UtenteDTO();
			utentePizza.setId(pizza.getUtente().getId());
			utentePizza.setUsername(pizza.getUtente().getUsername());

			pizzaMorph.setUtente(utentePizza);
			//Setto gli ingredienti della pizza
			List<IngredienteDTO> ingredientePizza = createIngredienteList(pizza.getIngrediente());
			//Infine setto la pizza
			pizzaMorph.setIngredienti(ingredientePizza);
			pizze.add(pizzaMorph);
		});
		impasto_dto.setPizza(pizze);
		return impasto_dto;
	}


	public static PizzaDTO convertToPizzaDTO(Pizza pizza){
		PizzaDTO pizza_dto = new PizzaDTO();
		pizza_dto.setId(pizza.getId());
		pizza_dto.setNome(pizza.getNome());
		//setto impasto
		ImpastoDTO impasto_dto = new ImpastoDTO();
		impasto_dto.setId(pizza.getImpasto().getId());
		impasto_dto.setNome(pizza.getImpasto().getNome());
		pizza_dto.setImpasto(impasto_dto);
		//setto utente
		UtenteDTO utente_dto = new UtenteDTO();
		utente_dto.setId(pizza.getUtente().getId());
		utente_dto.setUsername((pizza.getUtente().getUsername()));
		pizza_dto.setUtente(utente_dto);
		//setto ingrediente
		List<IngredienteDTO> listaIngre = createIngredienteList(pizza.getIngrediente());
		pizza_dto.setIngredienti(listaIngre);

		return pizza_dto;
	}

	public static UtenteDTO convertToUtenteDTO(Utente utente) {
		UtenteDTO utente_dto = new UtenteDTO();
		utente_dto.setId(utente.getId());
		utente_dto.setUsername(utente.getUsername());
		utente_dto.setPassword(utente.getPassword());

		List<PizzaDTO> p = new ArrayList<PizzaDTO>();
		utente.getPizza().forEach(pizza ->{
			//Creo funzione che mi fa direttamente tutto questo tramite
			//una lista di pizzaDTO
			PizzaDTO y = new PizzaDTO();
			ImpastoDTO z = new ImpastoDTO();

			z.setNome(pizza.getImpasto().getNome());
			z.setId(pizza.getImpasto().getId());
			
			y.setImpasto(z);
			y.setNome(pizza.getNome());
			y.setId(pizza.getId());

			List<IngredienteDTO> u = createIngredienteList(pizza.getIngrediente());
			y.setIngredienti(u);

			p.add(y);			
		});
		utente_dto.setPizza(p);
		return utente_dto;
	}
	
	
}
