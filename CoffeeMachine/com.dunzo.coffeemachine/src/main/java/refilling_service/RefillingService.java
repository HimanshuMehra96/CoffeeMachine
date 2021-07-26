package main.java.refilling_service;

import main.java.exception_handling.IngredientNotAvailableException;
import main.java.exception_handling.IngredientStorageOverflowException;
import main.java.model.IngredientStore;

//Service for refilling storages
public interface RefillingService {
	public void refillCompletely(String ingredient)
			throws IngredientStorageOverflowException, IngredientNotAvailableException;
	
	public void refill(String ingredient, Integer incrementQuantity)
			throws IngredientStorageOverflowException, IngredientNotAvailableException;
	
	public void refillCompletely(IngredientStore ingredientStore)
			throws IngredientStorageOverflowException;
	
	public void refill(IngredientStore ingredientStore, Integer incrementQuantity)
			throws IngredientStorageOverflowException;
}
