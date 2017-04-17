package solidpatterns.alexa.models;

public class Ingredient {
	private String name;
	private int alcoholPercentage;

	public Ingredient(String name, int alcoholPercentage) {
		this.name = name;
		this.alcoholPercentage = alcoholPercentage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(int alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	public String getAlexaFriendlyName() {
		return toString();
	}

	@Override
	public String toString() {
		return name;
	}
}
