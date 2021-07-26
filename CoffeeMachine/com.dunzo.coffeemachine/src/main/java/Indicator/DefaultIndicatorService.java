package main.java.Indicator;

import main.java.exception_handling.IngredientStorageOverflowException;
import main.java.model.IngredientStore;

//default strategy is to have storage for each ingredient
//each storage has its own threshold value
//if ingredient drops below the threshold value Indicator activates
public class DefaultIndicatorService implements IndicatorService{
	
	@Override
	public void checkForOverflow(IngredientStore ingredientStore)
			throws IngredientStorageOverflowException {
		if(ingredientStore.getIngredient().getQuantity() > ingredientStore.getMaxCapacity()) {
			String error = ingredientStore.getIngredient().getName() + " could not be added due to overflow";
			throw new IngredientStorageOverflowException(error);
		}
	}
	
	@Override
	public void checkForThreshold(IngredientStore ingredientStore) {
		if(ingredientStore.getIngredient().getQuantity() < ingredientStore.getThreshold()) {
			//call the indicator for that particular ingredient storage
			System.out.println("INDICATOR "+ ingredientStore.getIngredient().getName() + " is getting low");
		}
	}
}
