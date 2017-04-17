package solidpatterns.alexa.services;

import solidpatterns.alexa.models.Cocktail;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface OysterBarService {

	List<Cocktail> getCocktails();

	Optional<Cocktail> findCocktail(String cocktailName);
}
