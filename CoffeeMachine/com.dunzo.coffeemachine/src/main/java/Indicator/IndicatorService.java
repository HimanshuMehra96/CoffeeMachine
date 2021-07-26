package main.java.Indicator;

import main.java.exception_handling.IngredientStorageOverflowException;
import main.java.model.IngredientStore;

//IndicatorService works over Storage.
//provides checks for items being inserted or removed from IngredientStorage
public interface IndicatorService {
	public void checkForThreshold(IngredientStore ingredientStore);
	
	public void checkForOverflow(IngredientStore ingredientStore)
			throws IngredientStorageOverflowException;
}
