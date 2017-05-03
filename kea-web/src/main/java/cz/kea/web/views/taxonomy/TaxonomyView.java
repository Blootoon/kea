package cz.kea.web.views.taxonomy;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.model.Taxon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = TaxonomyView.VIEW_NAME)
public class TaxonomyView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(TaxonomyView.class);

    public static final String VIEW_NAME = "taxonomy";

    private TreeGrid<Taxon> taxonGrid;

    @PostConstruct
    public void init() {
        taxonGrid = new TreeGrid<>();

        addComponent(taxonGrid);
        setExpandRatio(taxonGrid, 1.0f);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        LOG.trace("In taxonomy view.");
    }
}
