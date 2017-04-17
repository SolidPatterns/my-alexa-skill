package solidpatterns.alexa.services;

import solidpatterns.alexa.domain.DummyInput;
import solidpatterns.alexa.models.Dummy;

/**
 *
 */
public interface DummyService {

	Dummy getDummy(DummyInput dummyInput);
}
