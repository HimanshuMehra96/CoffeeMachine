package main.java.storage;

import java.util.ArrayList;

import main.java.exception_handling.BeverageRecipeNotAvailableException;
import main.java.exception_handling.IngredientNotAvailableException;
import main.java.exception_handling.IngredientNotSufficientException;
import main.java.exception_handling.IngredientStorageOverflowException;
import main.java.model.BeverageRecipe;
import main.java.model.Ingredient;
import main.java.model.IngredientStore;

public interface Storage {
	public BeverageRecipe getBeverageRecipeUsingName(String beverageName)
			throws BeverageRecipeNotAvailableException;
	
	public IngredientStore getIngredientStoreUsingName(String ingredientName)
			throws IngredientNotAvailableException;
	
	public Ingredient getIngredientFromStore(String ingredientName)
			throws IngredientNotAvailableException;
	
	public void useIngredientFromStore(Ingredient ingredient)
			throws IngredientNotSufficientException, IngredientNotAvailableException;
	
	public IngredientStore increaseIngredientQuantity(IngredientStore ingredientStore, Integer Quantity)
			throws IngredientStorageOverflowException;
	
	public void addToIngredientStore(IngredientStore ingredientStore);
	
	public void addToIngredientStore(ArrayList<IngredientStore> ingredientStores);

	public IngredientStore decreaseIngredientQuantity(IngredientStore ingredientStore, Integer decrementQuantity)
			throws IngredientStorageOverflowException, IngredientNotSufficientException;

	public void addToBeverageRecipe(BeverageRecipe beverageRecipe);
	
	public void addToBeverageRecipe(ArrayList<BeverageRecipe> beverageRecipes);

	public String toString();
}
