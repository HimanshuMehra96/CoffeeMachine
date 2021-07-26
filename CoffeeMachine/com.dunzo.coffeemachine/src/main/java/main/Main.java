package main.java.main;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import main.java.Indicator.DefaultIndicatorService;
import main.java.Indicator.IndicatorService;
import main.java.coffee_machine.CoffeeMachine;
import main.java.exception_handling.MoreRequestsThanOutletsException;
import main.java.input.InputReader;
import main.java.input.ProcessInput;
import main.java.mixer_service.MixerService;
import main.java.model.BeverageRecipe;
import main.java.model.IngredientStore;
import main.java.refilling_service.DefaultRefillingService;
import main.java.refilling_service.RefillingService;
import main.java.storage.HashMapBasedStorage;
import main.java.storage.Storage;

public class Main {
	public static void main(String args[]) {
		final String INPUT = "/Input.json";
		final Integer THRESHOLD = 10;
		IndicatorService indicatorService = new DefaultIndicatorService();
		HashMap<String, IngredientStore> mapIngredientStore = new HashMap<>();
		HashMap<String, BeverageRecipe> mapBeverageRecipe = new HashMap<>();

		Storage storage = new HashMapBasedStorage(mapIngredientStore,
				mapBeverageRecipe, indicatorService);

		MixerService mixerService = new MixerService(storage);
		
		RefillingService refillingService = new DefaultRefillingService(storage);
		
		JSONObject inputJson = InputReader.readJsonFile(INPUT);
		ProcessInput processInput = new ProcessInput();
		ArrayList<BeverageRecipe> beverageRecipes =
				processInput.buildBeverageRecipeList(inputJson.getJSONObject("machine").getJSONObject("beverages"));
		
		Integer outlets = inputJson.getJSONObject("machine").getJSONObject("outlets").getInt("count_n");
		CoffeeMachine coffeeMachine = new CoffeeMachine(outlets,mixerService,refillingService,storage);
		coffeeMachine.addBeverageRecipe(beverageRecipes);
		
		JSONObject ingredientStoreJson = inputJson.getJSONObject("machine").getJSONObject("total_items_quantity");
		ArrayList<IngredientStore> ingredientStores = 
				processInput.buildIngredientStoreList(ingredientStoreJson, THRESHOLD);
		coffeeMachine.addIngredientStore(ingredientStores);
		
		//serve hot_coffee : output : success
		coffeeMachine.serveDrink("hot_coffee");
		//serve green_tea : output failure since green_mixture is unavailable
		coffeeMachine.serveDrink("green_tea");
		//serve black_tea : output : success
		coffeeMachine.serveDrink("black_tea");
		//serve hot_tea : output : failure multiple ingredients are insufficient
		coffeeMachine.serveDrink("hot_tea");
		//fill storage for sugar_syrup
		coffeeMachine.refillIngredientCompletely("sugar_syrup");
		//serve hot_tea : output : failure insufficient hot_water
		coffeeMachine.serveDrink("hot_tea");
		//fill hot water storage
		coffeeMachine.refillIngredientCompletely("hot_water");
		// serve hot_tea output : success
		coffeeMachine.serveDrink("hot_tea");
		//increase sugar_syrup quantity by 10 storage now sugar_syrup is completely filled
		coffeeMachine.refillIngredient("sugar_syrup", 10);
		//increase sugar_syrup quantity by 10, overflow
		coffeeMachine.refillIngredient("sugar_syrup", 10);
		//invalid drink request
		coffeeMachine.serveDrink("hawai_punch");
		
		//fill all storages completely
		coffeeMachine.refillIngredientCompletely("hot_water");
		coffeeMachine.refillIngredientCompletely("hot_milk");
		coffeeMachine.refillIngredientCompletely("ginger_syrup");
		coffeeMachine.refillIngredientCompletely("sugar_syrup");
		coffeeMachine.refillIngredientCompletely("tea_leaves_syrup");
		
		ArrayList<String> order1 = new ArrayList<String>();
		order1.add("hot_tea");
		order1.add("black_tea");
		order1.add("green_tea");
		order1.add("hot_coffee");
		try {
			//serving from all 4 outltes
			coffeeMachine.serveMultipleDrinks(order1);
		} catch (MoreRequestsThanOutletsException e) {
			System.out.println(e.getMessage());
		}
		order1.add("hot_coffee");
		try {
			//serving 5 drinks ; output error not enough outlets
			coffeeMachine.serveMultipleDrinks(order1);
		} catch (MoreRequestsThanOutletsException e) {
			System.out.println(e.getMessage());
		}
	}
}
