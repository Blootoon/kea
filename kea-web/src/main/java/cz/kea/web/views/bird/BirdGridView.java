package cz.kea.web.views.bird;

import java.util.Iterator;
import java.util.Set;
import javax.annotation.PostConstruct;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.model.Bird;
import cz.kea.api.services.BirdService;
import cz.kea.web.components.helpers.ContactHelper;
import cz.kea.web.components.helpers.DateTimeHelper;
import cz.kea.web.components.helpers.EnumHelper;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.components.helpers.TaxonHelper;
import cz.kea.web.components.dataproviders.BirdDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = BirdGridView.VIEW_NAME)
public class BirdGridView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(BirdGridView.class);

    public static final String VIEW_NAME = "birds";

    @Autowired
    private BirdService birdService;

    @Autowired
    private BirdDataProvider birdDataProvider;

    @Autowired
    private TaxonHelper taxonHelper;

    @Autowired
    private ContactHelper contactHelper;

    @Autowired
    private EnumHelper enumHelper;

    @Autowired
    private DateTimeHelper dateTimeHelper;

    @Autowired
    private KeaMessageSource messageSource;

    private Button buttonNew;

    private Button buttonEdit;

    private Button buttonDelete;

    private Grid<Bird> grid;

    @PostConstruct
    public void init() {
        grid = new Grid<>();
        grid.setDataProvider(birdDataProvider);
        grid.addColumn(Bird::getId).setCaption(messageSource.get("id", getLocale()));
        grid.addColumn(item -> taxonHelper.nameGenerator(item.getTaxon()))
            .setCaption(messageSource.get("taxon", getLocale())).setDescriptionGenerator(item -> taxonHelper
            .descriptionGenerator(item.getTaxon()));
        grid.addColumn(item -> enumHelper.nameGenerator(item.getSex(), getLocale()))
            .setCaption(messageSource.get("sex", getLocale()));
        grid.addColumn(Bird::getMutation).setCaption(messageSource.get("mutation", getLocale()));
        grid.addColumn(Bird::getIdentification).setCaption(messageSource.get("identification", getLocale()));
        grid.addColumn(Bird::getName).setCaption(messageSource.get("name", getLocale()));
        grid.addColumn(item -> dateTimeHelper.dateFormatter(item.getLayed(), getLocale()))
            .setCaption(messageSource.get("layed", getLocale()));
        grid.addColumn(item -> dateTimeHelper.dateFormatter(item.getHatched(), getLocale()))
            .setCaption(messageSource.get("hatched", getLocale()));
        grid.addColumn(item -> enumHelper.nameGenerator(item.getState(), getLocale()))
            .setCaption(messageSource.get("state", getLocale()));
        grid.addColumn(item -> contactHelper.nameGenerator(item.getOwner()))
            .setCaption(messageSource.get("owner", getLocale()));
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.addItemClickListener(e -> onItemClick(e));
        grid.addSelectionListener(e -> onSelectionChange(e));

        HorizontalLayout toolbar = new HorizontalLayout();

        buttonNew = new Button(messageSource.get("new-record", getLocale()), e -> navigateToForm(null));
        toolbar.addComponent(buttonNew);

        buttonEdit = new Button(messageSource.get("edit-record", getLocale()), e -> editRecord());
        buttonEdit.setEnabled(false);
        toolbar.addComponent(buttonEdit);

        buttonDelete = new Button(messageSource.get("delete-record", getLocale()), e -> deleteRecord());
        buttonDelete.setEnabled(false);
        toolbar.addComponent(buttonDelete);

        addComponent(toolbar);
        addComponent(grid);
        setExpandRatio(grid, 1.0f);
    }

    private void onItemClick(Grid.ItemClick<Bird> event) {
        if (event.getMouseEventDetails().isDoubleClick()) {
            getUI().getNavigator().navigateTo(BirdFormView.VIEW_NAME + "/" + event.getItem().getId());
        }
    }

    private void onSelectionChange(SelectionEvent<Bird> event) {
        if (event.getAllSelectedItems().size() > 0) {
            buttonEdit.setEnabled(true);
            buttonDelete.setEnabled(true);
        } else {
            buttonEdit.setEnabled(false);
            buttonDelete.setEnabled(false);
        }
    }

    private void navigateToForm(Bird bird) {
        if (bird == null) {
            getUI().getNavigator().navigateTo(BirdFormView.VIEW_NAME);
        } else {
            getUI().getNavigator().navigateTo(BirdFormView.VIEW_NAME + "/" + bird.getId());
        }
    }

    private void editRecord() {
        Set<Bird> selected = grid.getSelectedItems();
        Iterator<Bird> iterator = selected.iterator();
        if (iterator.hasNext()) {
            navigateToForm(iterator.next());
        }
    }

    private void deleteRecord() {
        for (Bird bird : grid.getSelectedItems()) {
            birdService.delete(bird.getId());
        }
        grid.getDataProvider().refreshAll();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        LOG.trace("In birds view.");
    }
}
