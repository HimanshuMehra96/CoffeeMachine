package main.java.model;

import java.util.ArrayList;

//Recipe for each beverage is stored
//Immutable class for future multi-threading implementations
public final class BeverageRecipe {
	private final String name;
	
	@Override
	public String toString() {
		return "BeverageRecipe [name=" + name + ", ingredients=" + ingredients + "]";
	}

	private final ArrayList<Ingredient> ingredients;
	
	public BeverageRecipe(String name, ArrayList<Ingredient> ingredients) {
		this.name = name;
		this.ingredients = new ArrayList<Ingredient>(ingredients);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Ingredient> getIngredients() {
		return new ArrayList<Ingredient>(ingredients);
	}
}
