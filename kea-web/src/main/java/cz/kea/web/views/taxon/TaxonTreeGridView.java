package cz.kea.web.views.taxon;

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
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.model.Taxon;
import cz.kea.api.services.TaxonService;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.components.dataproviders.TaxonHierarchicalDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = TaxonTreeGridView.VIEW_NAME)
public class TaxonTreeGridView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(TaxonTreeGridView.class);

    public static final String VIEW_NAME = "taxons";

    @Autowired
    private TaxonService taxonService;

    @Autowired
    private TaxonHierarchicalDataProvider taxonHierarchicalDataProvider;

    @Autowired
    private KeaMessageSource messageSource;

    private TreeGrid<Taxon> treeGrid;

    private Button buttonNew;

    private Button buttonEdit;

    private Button buttonDelete;

    @PostConstruct
    public void init() {
        treeGrid = new TreeGrid<>();
        treeGrid.setDataProvider(taxonHierarchicalDataProvider);
        treeGrid.addColumn(Taxon::getId).setCaption(messageSource.get("id", getLocale()));
        treeGrid.addColumn(Taxon::getLatinName).setCaption(messageSource.get("latin-name", getLocale()));
        treeGrid.addColumn(Taxon::getCzechName).setCaption(messageSource.get("czech-name", getLocale()));
        treeGrid.addColumn(Taxon::getEnglishName).setCaption(messageSource.get("english-name", getLocale()));
        treeGrid.addColumn(Taxon::getGermanName).setCaption(messageSource.get("german-name", getLocale()));
        treeGrid.setWidth(100, Unit.PERCENTAGE);
        treeGrid.addItemClickListener(e -> onItemClick(e));
        treeGrid.addSelectionListener(e -> onSelectionChange(e));

        HorizontalLayout toolbar = new HorizontalLayout();

        buttonNew = new Button(messageSource.get("new-record", getLocale()), e -> createRecord());
        toolbar.addComponent(buttonNew);

        buttonEdit = new Button(messageSource.get("edit-record", getLocale()), e -> editRecord());
        buttonEdit.setEnabled(false);
        toolbar.addComponent(buttonEdit);

        buttonDelete = new Button(messageSource.get("delete-record", getLocale()), e -> deleteRecord());
        buttonDelete.setEnabled(false);
        toolbar.addComponent(buttonDelete);

        addComponent(toolbar);
        addComponent(treeGrid);
        setExpandRatio(treeGrid, 1.0f);
    }

    private void onItemClick(Grid.ItemClick<Taxon> event) {
        if (event.getMouseEventDetails().isDoubleClick()) {
            getUI().getNavigator().navigateTo(TaxonFormView.getEditUrl(event.getItem()));
        }
    }

    private void onSelectionChange(SelectionEvent<Taxon> event) {
        if (event.getAllSelectedItems().size() > 0) {
            buttonEdit.setEnabled(true);
            buttonDelete.setEnabled(true);
        } else {
            buttonEdit.setEnabled(false);
            buttonDelete.setEnabled(false);
        }
    }

    private void createRecord() {
        Set<Taxon> selected = treeGrid.getSelectedItems();
        Iterator<Taxon> iterator = selected.iterator();
        if (iterator.hasNext()) {
            Taxon taxon = iterator.next();
            getUI().getNavigator().navigateTo(TaxonFormView.getCreateUrl(taxon));
        } else {
            getUI().getNavigator().navigateTo(TaxonFormView.getCreateUrl(null));
        }
    }

    private void editRecord() {
        Set<Taxon> selected = treeGrid.getSelectedItems();
        Iterator<Taxon> iterator = selected.iterator();
        if (iterator.hasNext()) {
            Taxon taxon = iterator.next();
            getUI().getNavigator().navigateTo(TaxonFormView.getEditUrl(taxon));
        }
    }

    private void deleteRecord() {
        for (Taxon taxon : treeGrid.getSelectedItems()) {
            taxonService.delete(taxon.getId());
        }
        treeGrid.getDataProvider().refreshAll();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        LOG.trace("In taxonomy view.");
    }
}
