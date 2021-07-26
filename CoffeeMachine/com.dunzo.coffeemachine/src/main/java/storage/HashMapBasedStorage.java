package main.java.storage;

import java.util.ArrayList;
import java.util.HashMap;

import main.java.Indicator.IndicatorService;
import main.java.exception_handling.BeverageRecipeNotAvailableException;
import main.java.exception_handling.IngredientNotAvailableException;
import main.java.exception_handling.IngredientNotSufficientException;
import main.java.exception_handling.IngredientStorageOverflowException;
import main.java.model.BeverageRecipe;
import main.java.model.Ingredient;
import main.java.model.IngredientStore;

public class HashMapBasedStorage implements Storage{
	private HashMap<String, IngredientStore> mapIngredientStore;
	private HashMap<String, BeverageRecipe> mapBeverageRecipe;
	private IndicatorService indicatorService;

	public HashMapBasedStorage(HashMap<String, IngredientStore> mapIngredientStore,
			HashMap<String, BeverageRecipe> mapBeverageRecipe,
			IndicatorService indicatorService) {
		this.mapIngredientStore = mapIngredientStore;
		this.mapBeverageRecipe = mapBeverageRecipe;
		this.indicatorService = indicatorService;
	}
	
	/**
	 * @param beverageName
	 * @return
	 * @throws BeverageRecipeNotAvailableException
	 */
	@Override
	public BeverageRecipe getBeverageRecipeUsingName(String beverageName)
			throws BeverageRecipeNotAvailableException {
		if(mapBeverageRecipe.containsKey(beverageName)) {
			return mapBeverageRecipe.get(beverageName);
		}
		throw new BeverageRecipeNotAvailableException(beverageName + " is not available");
	}
	
	@Override
	public IngredientStore getIngredientStoreUsingName(String ingredientName)
			throws IngredientNotAvailableException {
		if(mapIngredientStore.containsKey(ingredientName)) {
			return mapIngredientStore.get(ingredientName);
		}
		throw new IngredientNotAvailableException("item " + ingredientName + " is not available");
	}

	@Override
	public Ingredient getIngredientFromStore(String ingredientName)
			throws IngredientNotAvailableException {
		IngredientStore ingrdientStore = getIngredientStoreUsingName(ingredientName);
		return ingrdientStore.getIngredient();
	}

	@Override
	public void useIngredientFromStore(Ingredient ingredient)
			throws IngredientNotSufficientException, IngredientNotAvailableException {
		IngredientStore ingredientStore = getIngredientStoreUsingName(ingredient.getName());
		decreaseIngredientQuantity(ingredientStore, ingredient.getQuantity());
		
	}
	
	@Override
	public IngredientStore increaseIngredientQuantity(IngredientStore ingredientStore, Integer incrementQuantity)
			throws IngredientStorageOverflowException {
		IngredientStore increasedIngredientStore = ingredientStore.increaseQuantity(incrementQuantity);
		indicatorService.checkForOverflow(increasedIngredientStore);
		addToIngredientStore(increasedIngredientStore);
		return increasedIngredientStore;
	}
	
	@Override
	public IngredientStore decreaseIngredientQuantity(IngredientStore ingredientStore, Integer decrementQuantity)
			throws IngredientNotSufficientException {
		IngredientStore decreasedIngredientStore = ingredientStore.decreaseQuantity(decrementQuantity);
		if(decreasedIngredientStore.getIngredient().getQuantity()<0) {
			throw new IngredientNotSufficientException(ingredientStore.getIngredient());
		}
		addToIngredientStore(decreasedIngredientStore);
		return decreasedIngredientStore;
	}

	@Override
	public void addToIngredientStore(IngredientStore ingredientStore){
		indicatorService.checkForThreshold(ingredientStore);
		mapIngredientStore.put(ingredientStore.getIngredient().getName(), ingredientStore);
	}
	
	@Override
	public void addToIngredientStore(ArrayList<IngredientStore> ingredientStores){
		for(IngredientStore ingredientStore : ingredientStores) {
			addToIngredientStore(ingredientStore);
		}
	}
	
	@Override
	public void addToBeverageRecipe(BeverageRecipe beverageRecipe){
		mapBeverageRecipe.put(beverageRecipe.getName(), beverageRecipe);
	}
	
	@Override
	public void addToBeverageRecipe(ArrayList<BeverageRecipe> beverageRecipes){
		for(BeverageRecipe beverageRecipe : beverageRecipes) {
			addToBeverageRecipe(beverageRecipe);
		}
	}

	@Override
	public String toString() {
		return "HashMapBasedStorage [mapIngredientStore=" + mapIngredientStore + ", mapBeverageRecipe="
				+ mapBeverageRecipe + ", indicatorService=" + indicatorService + "]";
	}
	
}
