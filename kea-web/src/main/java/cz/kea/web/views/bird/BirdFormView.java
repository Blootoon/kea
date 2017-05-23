package cz.kea.web.views.bird;

import java.util.Arrays;
import java.util.Collections;
import javax.annotation.PostConstruct;

import com.vaadin.data.Binder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.enums.Sex;
import cz.kea.api.enums.State;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;
import cz.kea.api.model.Nest;
import cz.kea.api.model.Taxon;
import cz.kea.api.services.BirdService;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.components.dataproviders.ContactDataProvider;
import cz.kea.web.components.dataproviders.NestDataProvider;
import cz.kea.web.components.dataproviders.TaxonDataProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = BirdFormView.VIEW_NAME)
public class BirdFormView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(BirdFormView.class);

    public static final String VIEW_NAME = "bird";

    @Autowired
    private BirdService birdService;

    @Autowired
    private KeaMessageSource messageSource;

    @Autowired
    private NestDataProvider nestDataProvider;

    @Autowired
    private TaxonDataProvider taxonDataProvider;

    @Autowired
    private ContactDataProvider contactDataProvider;

    private Binder<Bird> binder;

    private Bird bean;

    @PostConstruct
    public void init() {
        FormLayout formLayout = new FormLayout();
        addComponent(formLayout);

        ComboBox<Taxon> taxon = new ComboBox<>(messageSource.get("taxon", getLocale()));
        taxon.setDataProvider(taxonDataProvider, filter -> Collections.emptyMap());
        taxon.setItemCaptionGenerator(item -> item.getCzechName());
        formLayout.addComponent(taxon);

        ComboBox<Sex> sex = new ComboBox<>(messageSource.get("sex", getLocale()));
        sex.setDataProvider(new ListDataProvider<Sex>(Arrays.asList(Sex.values())));
        sex.setItemCaptionGenerator(item -> messageSource.get(item, getLocale()));
        formLayout.addComponent(sex);

        ComboBox<State> state = new ComboBox<>(messageSource.get("state", getLocale()));
        state.setDataProvider(new ListDataProvider<State>(Arrays.asList(State.values())));
        state.setItemCaptionGenerator(item -> messageSource.get(item, getLocale()));
        formLayout.addComponent(state);

        ComboBox<Nest> nest = new ComboBox<>(messageSource.get("nest", getLocale()));
        nest.setDataProvider(nestDataProvider, filter -> Collections.emptyMap());
        nest.setItemCaptionGenerator(item -> String.valueOf(item.getId()));
        formLayout.addComponent(nest);

        DateField layed = new DateField(messageSource.get("layed", getLocale()));
        formLayout.addComponent(layed);

        DateField hatched = new DateField(messageSource.get("hatched", getLocale()));
        formLayout.addComponent(hatched);

        TextField mutation = new TextField(messageSource.get("mutation", getLocale()));
        formLayout.addComponent(mutation);

        TextField identification = new TextField(messageSource.get("identification", getLocale()));
        formLayout.addComponent(identification);

        TextField name = new TextField(messageSource.get("name", getLocale()));
        formLayout.addComponent(name);

        ComboBox<Contact> owner = new ComboBox<>(messageSource.get("contact", getLocale()));
        owner.setDataProvider(contactDataProvider, filter -> Collections.emptyMap());
        owner.setItemCaptionGenerator(item -> item.getFullName());
        formLayout.addComponent(owner);

        TextArea note = new TextArea(messageSource.get("note", getLocale()));
        formLayout.addComponent(note);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button save = new Button(messageSource.get("save", getLocale()), e -> save(e));
        save.setDisableOnClick(true);
        buttonLayout.addComponent(save);

        Button cancel = new Button(messageSource.get("cancel", getLocale()), e -> cancel(e));
        buttonLayout.addComponent(cancel);

        formLayout.addComponent(buttonLayout);

        binder = new Binder<>(Bird.class);
        binder.bind(taxon, "taxon");
        binder.bind(sex, "sex");
        binder.bind(state, "state");
        binder.bind(nest, "nest");
        binder.bind(layed, "layed");
        binder.bind(hatched, "hatched");
        binder.bind(mutation, "mutation");
        binder.bind(identification, "identification");
        binder.bind(name, "name");
        binder.bind(owner, "owner");
        binder.bind(note, "note");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (StringUtils.isNotEmpty(event.getParameters())) {
            long id = Long.parseLong(event.getParameters());

            bean = birdService.findOne(id);
        } else {
            bean = birdService.create();
        }

        binder.readBean(bean);
    }

    private void save(Button.ClickEvent event) {
        try {
            binder.writeBeanIfValid(bean);

            birdService.saveAndFlush(bean);

            getUI().getNavigator().navigateTo(BirdGridView.VIEW_NAME);

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
        getUI().getNavigator().navigateTo(BirdGridView.VIEW_NAME);
    }
}
