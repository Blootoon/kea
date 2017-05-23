package cz.kea.web.views.pair;

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
import cz.kea.api.model.Pair;
import cz.kea.api.services.PairService;
import cz.kea.web.components.BirdHelper;
import cz.kea.web.components.DateTimeHelper;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.dataproviders.PairDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = PairGridView.VIEW_NAME)
public class PairGridView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(PairGridView.class);

    public static final String VIEW_NAME = "pairs";

    @Autowired
    private PairService pairService;

    @Autowired
    private PairDataProvider pairDataProvider;

    @Autowired
    private BirdHelper birdHelper;

    @Autowired
    private DateTimeHelper dateTimeHelper;

    @Autowired
    private KeaMessageSource messageSource;

    private Button buttonNew;

    private Button buttonEdit;

    private Button buttonDelete;

    private Grid<Pair> grid;

    @PostConstruct
    public void init() {
        grid = new Grid<>();
        grid.setDataProvider(pairDataProvider);
        grid.addColumn(Pair::getId).setCaption(messageSource.get("id", getLocale()));
        grid.addColumn(item -> birdHelper.nameGenerator(item.getMale()))
            .setCaption(messageSource.get("male", getLocale()))
            .setDescriptionGenerator(item -> birdHelper.descriptionGenerator(item.getMale()));
        grid.addColumn(item -> birdHelper.nameGenerator(item.getFemale()))
            .setCaption(messageSource.get("female", getLocale()))
            .setDescriptionGenerator(item -> birdHelper.descriptionGenerator(item.getFemale()));
        grid.addColumn(item -> dateTimeHelper.dateFormatter(item.getDateFrom(), getLocale()))
            .setCaption(messageSource.get("date-from", getLocale()));
        grid.addColumn(item -> dateTimeHelper.dateFormatter(item.getDateTo(), getLocale()))
            .setCaption(messageSource.get("date-to", getLocale()));
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

    private void onItemClick(Grid.ItemClick<Pair> event) {
        if (event.getMouseEventDetails().isDoubleClick()) {
            navigateToForm(event.getItem());
        }
    }

    private void onSelectionChange(SelectionEvent<Pair> event) {
        if (event.getAllSelectedItems().size() > 0) {
            buttonEdit.setEnabled(true);
            buttonDelete.setEnabled(true);
        } else {
            buttonEdit.setEnabled(false);
            buttonDelete.setEnabled(false);
        }
    }

    private void navigateToForm(Pair pair) {
        if (pair == null) {
            getUI().getNavigator().navigateTo(PairFormView.VIEW_NAME);
        } else {
            getUI().getNavigator().navigateTo(PairFormView.VIEW_NAME + "/" + pair.getId());
        }
    }

    private void editRecord() {
        Set<Pair> selected = grid.getSelectedItems();
        Iterator<Pair> iterator = selected.iterator();
        if (iterator.hasNext()) {
            navigateToForm(iterator.next());
        }
    }

    private void deleteRecord() {
        for (Pair pair : grid.getSelectedItems()) {
            pairService.delete(pair.getId());
        }
        grid.getDataProvider().refreshAll();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        LOG.trace("In birds view.");
    }
}
