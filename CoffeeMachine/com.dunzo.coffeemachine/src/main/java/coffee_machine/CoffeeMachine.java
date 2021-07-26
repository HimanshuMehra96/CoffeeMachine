package main.java.coffee_machine;

import java.util.ArrayList;

import main.java.exception_handling.BeverageRecipeNotAvailableException;
import main.java.exception_handling.IngredientNotAvailableException;
import main.java.exception_handling.IngredientNotSufficientException;
import main.java.exception_handling.IngredientStorageOverflowException;
import main.java.exception_handling.MoreRequestsThanOutletsException;
import main.java.mixer_service.MixerService;
import main.java.model.BeverageRecipe;
import main.java.model.IngredientStore;
import main.java.refilling_service.RefillingService;
import main.java.storage.Storage;

//coffeMachine controls functionalities provided by coffee machine
public class CoffeeMachine {
	private final Integer outlets;
	private MixerService mixerService;
	private RefillingService refillingService;
	private Storage storage;
	
	public CoffeeMachine(Integer outlets, MixerService mixerService, RefillingService refillingService,
			Storage storage) {
		super();
		this.outlets = outlets;
		this.mixerService = mixerService;
		this.refillingService = refillingService;
		this.storage = storage;
	}
	
	public void serveMultipleDrinks(ArrayList<String> beverageList)
			throws MoreRequestsThanOutletsException {
		if(beverageList.size()>outlets) {
			String errorMsg = beverageList.size() + " requests cannot be handeled as only "
		+ outlets +" outlets are present";
			throw new MoreRequestsThanOutletsException(errorMsg);
		}
		for(String beverage: beverageList) {
			serveDrink(beverage);
		}
	}
	
	public void serveDrink(String beverage) {
		try {
			mixerService.makeBeverage(beverage);
			System.out.println(beverage + " is served");
		} catch (IngredientNotAvailableException |
				BeverageRecipeNotAvailableException | IngredientNotSufficientException e) {
			System.out.println(beverage +" cannot be prepared because "+e.getMessage());
		}
	}
	
	public void addBeverageRecipe(BeverageRecipe beverageRecipe) {
		storage.addToBeverageRecipe(beverageRecipe);
	}
	
	public void addBeverageRecipe(ArrayList<BeverageRecipe> beverageRecipes) {
		storage.addToBeverageRecipe(beverageRecipes);
	}
	
	public void addIngredientStore(IngredientStore ingredientStore) {
		storage.addToIngredientStore(ingredientStore);
	}
	
	public void addIngredientStore(ArrayList<IngredientStore> ingredientStores) {
		storage.addToIngredientStore(ingredientStores);
	}
	
	public void refillIngredientCompletely(String ingredientName) {
		try {
			refillingService.refillCompletely(ingredientName);
		} catch (IngredientStorageOverflowException | IngredientNotAvailableException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void refillIngredient(String ingredientName, Integer incerementQuantity) {
		try {
			refillingService.refill(ingredientName, incerementQuantity);
		} catch (IngredientStorageOverflowException | IngredientNotAvailableException e) {
			System.out.println(e.getMessage());
		}
	}
}
