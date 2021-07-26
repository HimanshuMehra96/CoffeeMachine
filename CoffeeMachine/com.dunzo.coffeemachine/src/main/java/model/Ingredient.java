package main.java.model;

//Each ingredient is represented by name and its quantity
//Immutable class for future multi-threading implementations
public final class Ingredient {
	private final String name;
	private final Integer quantity;
	
	public Ingredient(String name, Integer quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public Ingredient increaseQuantity(Integer incrementQuantity) {
		return new Ingredient(name, quantity+incrementQuantity);
	}
	
	public Ingredient decreaseQuantity(Integer decrementQuantity) {
		return new Ingredient(name, quantity-decrementQuantity);
	}

	@Override
	public String toString() {
		return "Ingredient [name=" + name + ", quantity=" + quantity + "]";
	}
}
