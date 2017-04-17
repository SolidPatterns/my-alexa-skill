package solidpatterns.alexa.models;

import java.util.List;

/**
 *
 */
public class Cocktail {
	private String name;
	private List<Ingredient> ingredients;

	public Cocktail(String name, List<Ingredient> ingredients) {
		this.name = name;
		this.ingredients = ingredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getAlexaFriendlyName() {
		return toString();
	}

	@Override
	public String toString() {
		String cocktailPrefix = name + " is a cocktail that consists of: ";
		StringBuilder cocktailDescriptionBuilder = new StringBuilder(cocktailPrefix);

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            cocktailDescriptionBuilder.append(i+1 + ". : ");
            cocktailDescriptionBuilder.append(ingredient.getAlexaFriendlyName() + ". ");
        }

        return cocktailDescriptionBuilder.toString();
	}
}

