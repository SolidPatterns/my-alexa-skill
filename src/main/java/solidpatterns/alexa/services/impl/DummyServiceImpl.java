package solidpatterns.alexa.services.impl;

import solidpatterns.alexa.domain.DummyInput;
import solidpatterns.alexa.models.Dummy;
import solidpatterns.alexa.services.DummyService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DummyServiceImpl implements DummyService {
	@Override
	public Dummy getDummy(DummyInput dummyInput) {
		Dummy dummy = new Dummy();
		dummy.setText(dummyInput.getText());
		return dummy;
	}
}
