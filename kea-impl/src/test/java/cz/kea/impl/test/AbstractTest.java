package cz.kea.impl.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:test-spring-context/test-application-context.xml",
    "classpath:test-spring-context/database.xml", "classpath:spring-context/kea-jpa.xml",
})
@Transactional
public abstract class AbstractTest {

    @Autowired
    protected ApplicationContext applicationContext;
}
