package cz.kea.web.views;

import javax.annotation.PostConstruct;

import com.vaadin.data.provider.GridSortOrderBuilder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.model.Bird;
import cz.kea.web.dataproviders.BirdsDataProvider;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = BirdsView.VIEW_NAME)
public class BirdsView extends VerticalLayout implements View {

    private static final Logger LOG = Logger.getLogger(BirdsView.class);

    public static final String VIEW_NAME = "birds";

    @Autowired
    private BirdsDataProvider birdsDataProvider;

    @Autowired
    private MessageSource messageSource;

    private Grid<Bird> birdGrid;

    @PostConstruct
    public void init() {
        birdGrid = new Grid<>();
        birdGrid.setDataProvider(birdsDataProvider);
        birdGrid.addColumn(Bird::getId).setCaption(messageSource.getMessage("birds-id", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getGenus).setCaption(messageSource.getMessage("birds-genus", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getSpecies).setCaption(messageSource.getMessage("birds-species", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getSex).setCaption(messageSource.getMessage("birds-sex", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getMutation).setCaption(messageSource.getMessage("birds-mutation", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getIdentification).setCaption(messageSource.getMessage("birds-identification", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getName).setCaption(messageSource.getMessage("birds-name", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getLayed).setCaption(messageSource.getMessage("birds-layed", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getHatched).setCaption(messageSource.getMessage("birds-hatched", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getState).setCaption(messageSource.getMessage("birds-state", new Object[]{}, getLocale()));
        birdGrid.addColumn(Bird::getOwner).setCaption(messageSource.getMessage("birds-owner", new Object[]{}, getLocale()));
        birdGrid.setWidth(100, Unit.PERCENTAGE);

        addComponent(birdGrid);
        setExpandRatio(birdGrid, 1.0f);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        LogMF.trace(LOG, "In birds view.", new Object[]{});
    }
}
