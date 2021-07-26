package main.java.refilling_service;

import main.java.exception_handling.IngredientNotAvailableException;
import main.java.exception_handling.IngredientStorageOverflowException;
import main.java.model.IngredientStore;
import main.java.storage.Storage;

//default strategy is to refill completely or provide increment quantity
public class DefaultRefillingService implements RefillingService{
	private Storage storage;

	public DefaultRefillingService(Storage storage) {
		super();
		this.storage = storage;
	}

	@Override
	public void refillCompletely(IngredientStore ingredientStore)
			throws IngredientStorageOverflowException {
		Integer incrementQuantity =
				ingredientStore.getMaxCapacity()-ingredientStore.getIngredient().getQuantity();
		IngredientStore incIngredientStore = storage.increaseIngredientQuantity(ingredientStore, incrementQuantity);
		String msg = incIngredientStore.getIngredient().getName() 
				+" storage filled completely current quantity " +
				incIngredientStore.getIngredient().getQuantity();
		System.out.println(msg);
	}

	@Override
	public void refill(IngredientStore ingredientStore, Integer incrementQuantity)
			throws IngredientStorageOverflowException {
		IngredientStore incIngredientStore = storage.increaseIngredientQuantity(ingredientStore, incrementQuantity);
		String msg = incIngredientStore.getIngredient().getName() 
				+" storage refilled current quantity " +
				incIngredientStore.getIngredient().getQuantity();
		System.out.println(msg);
	}

	@Override
	public void refillCompletely(String ingredient)
			throws IngredientStorageOverflowException, IngredientNotAvailableException {
		IngredientStore ingredientStore = storage.getIngredientStoreUsingName(ingredient);
		refillCompletely(ingredientStore);
	}

	@Override
	public void refill(String ingredient, Integer incrementQuantity)
			throws IngredientStorageOverflowException, IngredientNotAvailableException {
		IngredientStore ingredientStore = storage.getIngredientStoreUsingName(ingredient);
		refill(ingredientStore, incrementQuantity);
	}	
}
