package solidpatterns.alexa.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import solidpatterns.alexa.models.Cocktail;
import solidpatterns.alexa.services.OysterBarService;

import java.util.List;
import java.util.Optional;


/**
 *
 */
@RestController
@RequestMapping(value = "/alexa")
public class AlexaController {
    private final Logger LOG = LoggerFactory.getLogger(AlexaController.class);

    @Autowired
	private OysterBarService oysterBarService;

    @RequestMapping(value = "/intro", method = RequestMethod.GET)
    public String intro() {
        LOG.info("Introduction call received..");
        return "Hey, for Emakina Hackaton we have built and implemented an alexa powered cocktail bar, called the Oyster Bar. At Oyster Bar, all cocktails are free. You can get your free cocktail by just asking the oyster bar for one! Why don't you go for a gin and tonic?";
    }

    @RequestMapping(value = "/cocktails", method = RequestMethod.GET)
    public String listCocktails() {
        LOG.info("List cocktails call received..");
        List<Cocktail> cocktails = oysterBarService.getCocktails();
        String cocktailListBaseResponse = "There are " + cocktails.size() + " available cocktails. These are : ";
        for (int i = 0; i < cocktails.size(); i++) {
            Cocktail cocktail = cocktails.get(i);
            cocktailListBaseResponse += cocktail.getName();
            if(i != cocktails.size() - 1) {
                cocktailListBaseResponse += ", ";
            } else {
                cocktailListBaseResponse += ".";
            }
        }

        return cocktailListBaseResponse;
    }

    @RequestMapping(value = "/cocktails/detailed", method = RequestMethod.GET)
    public String listCocktailsDetailed(@RequestParam(value = "cocktailName") String cocktailName) {
        LOG.info("List cocktails detailed call received with a cocktail name of {}..", cocktailName);
        Optional<Cocktail> opCocktail = oysterBarService.findCocktail(cocktailName);

        if(opCocktail.isPresent()) {
            return opCocktail.get().getAlexaFriendlyName();
        }

        return "Sorry, I couldn't find any cocktail information.";
    }
}
