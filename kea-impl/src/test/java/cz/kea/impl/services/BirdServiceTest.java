package cz.kea.impl.services;

import cz.kea.api.enums.Sex;
import cz.kea.api.enums.State;
import cz.kea.api.model.Bird;
import cz.kea.api.services.BirdService;
import cz.kea.impl.test.AbstractTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public class BirdServiceTest extends AbstractTest {

    @Autowired
    private BirdService birdService;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSave() {
        Bird bird = birdService.create();
        bird.setSex(Sex.MALE);
        bird.setState(State.ADULT);
        bird.setIdentification("CZ 15 1234");
        bird.setName("Toníček");
        bird.setNote("Poznámka");
        Bird savedBird = birdService.save(bird);

        assertEquals(bird.getTaxon(), savedBird.getTaxon());
        assertEquals(bird.getSex(), savedBird.getSex());
        assertEquals(bird.getIdentification(), savedBird.getIdentification());
        assertEquals(bird.getName(), savedBird.getName());
        assertEquals(bird.getNote(), savedBird.getNote());
    }

    @Test
    public void testChangeStateSuccess() {
        Bird bird = birdService.create();
        bird.setSex(Sex.MALE);
        bird.setState(State.EGG);
        Bird savedBird = birdService.save(bird);

        Bird changedStateBird = birdService.changeState(savedBird, State.HATCHED);

        assertEquals(changedStateBird.getState(), State.HATCHED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeStateFail() {
        Bird bird = birdService.create();
        bird.setSex(Sex.MALE);
        bird.setState(State.EGG);
        Bird savedBird = birdService.save(bird);

        birdService.changeState(savedBird, State.ADULT);
    }

}
