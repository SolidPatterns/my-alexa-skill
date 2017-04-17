package solidpatterns.alexa.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import solidpatterns.alexa.models.Cocktail;
import solidpatterns.alexa.models.Ingredient;
import solidpatterns.alexa.services.OysterBarService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class OysterBarServiceImpl implements OysterBarService {

    private static List<Cocktail> cocktails =
            Arrays.asList(new Cocktail("Lynchburg Lemonade"
                                  , Arrays.asList(new Ingredient("Jack Daniel's No 7 Whiskey", 45),
                                                  new Ingredient("Contreau", 35),
                                                  new Ingredient("Sour mix", 0),
                                                  new Ingredient("Lemon lime", 0))),
                          new Cocktail("Gin & tonic"
                                  , Arrays.asList(new Ingredient("Hendriks gin", 45),
                                                  new Ingredient("Tonic", 0))),
                          new Cocktail("Long island ice tea"
                                  , Arrays.asList(new Ingredient("Triple sec", 30),
                                                  new Ingredient("Tequila", 45),
                                                  new Ingredient("Gin", 48),
                                                  new Ingredient("Vodka", 43),
                                                  new Ingredient("Rum", 41),
                                                  new Ingredient("Coca cola or ice tea", 0)))
            );

    public OysterBarServiceImpl() {
    }

    @Override
    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    @Override
    public Optional<Cocktail> findCocktail(String cocktailName) {
        return cocktails.stream().filter(x -> StringUtils.getLevenshteinDistance(cocktailName, x.getName()) <= 4).findAny();
    }
}
