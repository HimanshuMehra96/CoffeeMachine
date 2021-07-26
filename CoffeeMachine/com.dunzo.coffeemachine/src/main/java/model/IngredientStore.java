package main.java.model;

//Represents storage for each ingredient
//each storage has its threshold, which
//activates Indicator as level drops below threshold
//each storage has max_capacity to check for overflows
//Immutable class for future multi-threading implementations
public final class IngredientStore {
	private final Ingredient ingredient;
	private final Integer maxCapacity;
	private final Integer threshold;
	
	public IngredientStore(Ingredient ingredient, Integer maxCapacity, Integer threshold) {
		super();
		this.ingredient = ingredient;
		this.maxCapacity = maxCapacity;
		this.threshold = threshold;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public Integer getThreshold() {
		return threshold;
	}
	
	public IngredientStore increaseQuantity(Integer incrementQuantity) {
		Ingredient increasedIngredient = ingredient.increaseQuantity(incrementQuantity);
		return new IngredientStore(increasedIngredient, maxCapacity, threshold);
	}
	
	public IngredientStore decreaseQuantity(Integer decrementQuantity) {
		Ingredient decreasedIngredient = ingredient.decreaseQuantity(decrementQuantity);
		return new IngredientStore(decreasedIngredient, maxCapacity, threshold);
	}

	@Override
	public String toString() {
		return "IngredientStore [ingredient=" + ingredient + ", maxCapacity=" + maxCapacity + ", threshold=" + threshold
				+ "]";
	}
}
