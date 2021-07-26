package main.java.input;

import java.util.ArrayList;

import org.json.JSONObject;

import main.java.model.BeverageRecipe;
import main.java.model.Ingredient;
import main.java.model.IngredientStore;

public class ProcessInput {

	public ProcessInput() {
		super();
	}
	
	public ArrayList<BeverageRecipe> buildBeverageRecipeList(JSONObject jsonObject){
		ArrayList<BeverageRecipe> beverageRecipes = new ArrayList<>();
		try {
			jsonObject.keySet().forEach(keyStr ->
			{
			    JSONObject keyValue = jsonObject.getJSONObject(keyStr);
			    String beverageName = keyStr;
			    ArrayList<Ingredient> ingredientList = buildIngredientList(keyValue);
			    BeverageRecipe beverageRecipe = new BeverageRecipe(beverageName, ingredientList);
			    beverageRecipes.add(beverageRecipe);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beverageRecipes;
	}
	
	public ArrayList<Ingredient> buildIngredientList(JSONObject jsonObject){
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		try {
			jsonObject.keySet().forEach(keyStr ->
			{
				Object keyValue = jsonObject.get(keyStr);
			    String ingredientName = keyStr;
			    Integer quantity = Integer.parseInt(keyValue.toString());
			    Ingredient ingredient = new Ingredient(ingredientName,quantity);
			    ingredients.add(ingredient);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return ingredients;
	}
	
	public ArrayList<IngredientStore> buildIngredientStoreList(JSONObject jsonObject, Integer threshold){
		ArrayList<IngredientStore> ingredientStores = new ArrayList<>();
		
		try {
			jsonObject.keySet().forEach(keyStr ->
			{
				Object keyValue = jsonObject.get(keyStr);
				String ingredientName = keyStr;
				Integer quantity = Integer.parseInt(keyValue.toString());
				Ingredient ingredient = new Ingredient(ingredientName,quantity);
				//same threshold is considered
				// also inital quantity is assumed as max quantity
				IngredientStore ingredientStore = new IngredientStore(ingredient,quantity,threshold);
				ingredientStores.add(ingredientStore);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingredientStores;
	}
}
