package cz.kea.impl.services;

import cz.kea.api.enums.Genus;
import cz.kea.api.enums.Sex;
import cz.kea.api.enums.Species;
import cz.kea.api.enums.State;
import cz.kea.api.factories.model.BirdFactory;
import cz.kea.api.model.Bird;
import cz.kea.api.services.BirdService;
import cz.kea.impl.test.AbstractTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public class BirdServiceTest extends AbstractTest {

    @Autowired
    private BirdService birdService;

    @Autowired
    private BirdFactory birdFactory;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSave() {
        Bird bird = birdFactory.createBird(Genus.FORPUS, Species.FORPUS_COELESTIS, Sex.MALE, State.ADULT, null, null, null, null, "CZ 15 1234", "Toníček", null, "Poznámka");
        Bird savedBird = birdService.save(bird);

        assertEquals(bird.getGenus(), savedBird.getGenus());
        assertEquals(bird.getSpecies(), savedBird.getSpecies());
        assertEquals(bird.getSex(), savedBird.getSex());
        assertEquals(bird.getIdentification(), savedBird.getIdentification());
        assertEquals(bird.getName(), savedBird.getName());
        assertEquals(bird.getNote(), savedBird.getNote());
    }

    @Test
    public void testChangeStateSuccess() {
        Bird bird = birdFactory.createBird(Genus.FORPUS, Species.FORPUS_COELESTIS, Sex.MALE, State.EGG);
        Bird savedBird = birdService.save(bird);

        Bird changedStateBird = birdService.changeState(savedBird, State.HATCHED);

        assertEquals(changedStateBird.getState(), State.HATCHED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeStateFail() {
        Bird bird = birdFactory.createBird(Genus.FORPUS, Species.FORPUS_COELESTIS, Sex.MALE, State.EGG);
        Bird savedBird = birdService.save(bird);

        birdService.changeState(savedBird, State.ADULT);
    }

}
