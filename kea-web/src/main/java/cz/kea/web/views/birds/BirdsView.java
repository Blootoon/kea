package cz.kea.web.views.birds;

import javax.annotation.PostConstruct;
import javax.swing.*;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import cz.kea.api.model.Bird;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.dataproviders.BirdsDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = BirdsView.VIEW_NAME)
public class BirdsView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(BirdsView.class);

    public static final String VIEW_NAME = "birds";

    @Autowired
    private BirdsDataProvider birdsDataProvider;

    @Autowired
    private KeaMessageSource messageSource;

    private Grid<Bird> birdGrid;

    @PostConstruct
    public void init() {
        birdGrid = new Grid<>();
        birdGrid.setDataProvider(birdsDataProvider);
        birdGrid.addColumn(Bird::getId).setCaption(messageSource.get("birds-id", getLocale()));
        birdGrid.addColumn(Bird::getTaxon).setCaption(messageSource.get("birds-taxon", getLocale()));
        birdGrid.addColumn(Bird::getSex).setCaption(messageSource.get("birds-sex", getLocale()));
        birdGrid.addColumn(Bird::getMutation).setCaption(messageSource.get("birds-mutation", getLocale()));
        birdGrid.addColumn(Bird::getIdentification).setCaption(messageSource.get("birds-identification", getLocale()));
        birdGrid.addColumn(Bird::getName).setCaption(messageSource.get("birds-name", getLocale()));
        birdGrid.addColumn(Bird::getLayed).setCaption(messageSource.get("birds-layed", getLocale()));
        birdGrid.addColumn(Bird::getHatched).setCaption(messageSource.get("birds-hatched", getLocale()));
        birdGrid.addColumn(Bird::getState).setCaption(messageSource.get("birds-state", getLocale()));
        birdGrid.addColumn(Bird::getOwner).setCaption(messageSource.get("birds-owner", getLocale()));
        birdGrid.setWidth(100, Unit.PERCENTAGE);
        birdGrid.addItemClickListener(e -> onItemClick(e));

        addComponent(new Button("new", e -> getUI().getNavigator().navigateTo(BirdsFormView.VIEW_NAME)));
        addComponent(birdGrid);
        setExpandRatio(birdGrid, 1.0f);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        LOG.trace("In birds view.");
    }

    private void onItemClick(Grid.ItemClick<Bird> event) {
        if (event.getMouseEventDetails().isDoubleClick()) {
            getUI().getNavigator().navigateTo(BirdsFormView.VIEW_NAME + "/" + event.getItem().getId());
        }
    }
}
