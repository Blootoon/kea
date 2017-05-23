package cz.kea.web.views.taxon;

import java.util.Collections;
import javax.annotation.PostConstruct;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.factories.model.TaxonFactory;
import cz.kea.api.model.Taxon;
import cz.kea.api.services.TaxonService;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.components.TaxonHelper;
import cz.kea.web.dataproviders.TaxonDataProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = TaxonFormView.VIEW_NAME)
public class TaxonFormView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(TaxonFormView.class);

    public static final String VIEW_NAME = "taxon";

    public static final String ACTION_NEW = "new";
    public static final String ACTION_EDIT = "edit";

    @Autowired
    private TaxonService taxonService;

    @Autowired
    private TaxonFactory taxonFactory;

    @Autowired
    private TaxonDataProvider taxonDataProvider;

    @Autowired
    private TaxonHelper taxonHelper;

    @Autowired
    private KeaMessageSource messageSource;

    private Binder<Taxon> binder;

    private Taxon bean;

    @PostConstruct
    public void init() {
        FormLayout formLayout = new FormLayout();
        addComponent(formLayout);

        ComboBox<Taxon> parent = new ComboBox<>(messageSource.get("parent-taxon", getLocale()));
        parent.setDataProvider(taxonDataProvider, filter -> Collections.emptyMap());
        parent.setItemCaptionGenerator(item -> taxonHelper.nameGenerator(item));
        formLayout.addComponent(parent);

        TextField latinName = new TextField(messageSource.get("latin-name", getLocale()));
        formLayout.addComponent(latinName);

        TextField czechName = new TextField(messageSource.get("czech-name", getLocale()));
        formLayout.addComponent(czechName);

        TextField englishName = new TextField(messageSource.get("english-name", getLocale()));
        formLayout.addComponent(englishName);

        TextField germanName = new TextField(messageSource.get("german-name", getLocale()));
        formLayout.addComponent(germanName);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button save = new Button(messageSource.get("save", getLocale()), e -> save(e));
        save.setDisableOnClick(true);
        buttonLayout.addComponent(save);

        Button cancel = new Button(messageSource.get("cancel", getLocale()), e -> cancel(e));
        buttonLayout.addComponent(cancel);

        formLayout.addComponent(buttonLayout);

        binder = new Binder<>(Taxon.class);
        binder.bind(parent, "parent");
        binder.bind(latinName, "latinName");
        binder.bind(czechName, "czechName");
        binder.bind(englishName, "englishName");
        binder.bind(germanName, "germanName");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (StringUtils.isNotEmpty(event.getParameters())) {
            // parse query string
            String query = event.getParameters();

            String[] params = query.split("/");

            if (params.length == 2 && params[0].equals(ACTION_EDIT)) {
                long id = Long.parseLong(params[1]);
                bean = taxonService.findOne(id, true);
            } else if (params.length == 2 && params[0].equals(ACTION_NEW)) {
                long parentId = Long.parseLong(params[1]);
                bean = taxonFactory.createTaxon();
                bean.setParent(taxonService.findOne(parentId));
            } else if (params.length == 1 && params[0].equals(ACTION_NEW)) {
                bean = taxonFactory.createTaxon();
            } else {
                throw new IllegalStateException("Could not parse request URI.");
            }
        } else {
            bean = taxonFactory.createTaxon();
        }

        binder.readBean(bean);
    }

    private void save(Button.ClickEvent event) {
        try {
            binder.writeBeanIfValid(bean);

            taxonService.saveAndFlush(bean);

            getUI().getNavigator().navigateTo(TaxonTreeGridView.VIEW_NAME);

            LOG.info("Saving of %s successful.", bean);

            Notification.show(messageSource.get("saving-success", getLocale()), Notification.Type.TRAY_NOTIFICATION);
        } catch (Exception e) {
            LOG.error("Saving failed.", e);

            Notification.show(messageSource.get("saving-error", getLocale()), Notification.Type.ERROR_MESSAGE);
        } finally {
            event.getButton().setEnabled(true);
        }
    }

    private void cancel(Button.ClickEvent event) {
        getUI().getNavigator().navigateTo(TaxonTreeGridView.VIEW_NAME);
    }

    public static String getCreateUrl(Taxon parent) {
        if (parent == null) {
            return VIEW_NAME + "/" + ACTION_NEW;
        } else {
            return VIEW_NAME + "/" + ACTION_NEW + "/" + parent.getId();
        }
    }

    public static String getEditUrl(Taxon taxon) {
        Validate.notNull(taxon);
        return VIEW_NAME + "/" + ACTION_EDIT + "/" + taxon.getId();
    }
}
