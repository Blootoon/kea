package cz.kea.impl.services;

import cz.kea.api.enums.State;
import cz.kea.api.model.Bird;
import cz.kea.api.services.BirdService;
import cz.kea.impl.entities.BirdEntity;
import cz.kea.impl.factories.specifications.BirdSpecificationFactory;
import cz.kea.impl.factories.specifications.SpecificationFactory;
import cz.kea.impl.repositories.BaseRepository;
import cz.kea.impl.repositories.BirdRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Service
@Transactional
public class BirdServiceImpl extends BaseServiceImpl<Bird, Long> implements BirdService {

    @Autowired
    private BirdRepository birdRepository;

    @Autowired
    private BirdSpecificationFactory birdSpecificationFactory;

    @Override
    public Bird create() {
        return new BirdEntity();
    }

    @Override
    public Bird changeState(Bird bird, State newState) {
        Validate.notNull(bird, "Bird should not be null.");
        Validate.notNull(bird.getId(), "Bird ID should not be null.");
        Validate.notNull(bird.getState(), "Bird state should not be null.");

        if (bird.getState().isTransitionAllowed(newState)) {
            log.debug("Changing state of bird = %s from %s to %s.", bird.getId(), bird.getState(), newState);
            bird.setState(newState);
            return save(bird);
        } else {
            throw new IllegalArgumentException(String.format("Attempt to perform illegal state change from %s to %s.", bird.getState(), newState));
        }
    }

    @Override
    protected BaseRepository<BirdEntity, Long> getRepository() {
        return birdRepository;
    }

    @Override
    protected SpecificationFactory<BirdEntity, Long> getSpecificationFactory() {
        return birdSpecificationFactory;
    }
}
