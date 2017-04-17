package solidpatterns.alexa.controllers;

import solidpatterns.alexa.domain.DummyInput;
import solidpatterns.alexa.models.Dummy;
import solidpatterns.alexa.services.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping(value = "/test", method = RequestMethod.POST)
public class DummyController {

	@Autowired
	private DummyService dummyService;

    @RequestMapping("/dummy")
    public Dummy test(@RequestBody DummyInput dummyInput) {
        return dummyService.getDummy(dummyInput);
    }
}
