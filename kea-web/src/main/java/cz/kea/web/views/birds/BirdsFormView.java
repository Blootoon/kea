package cz.kea.web.views.birds;

import java.util.Arrays;
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
import cz.kea.api.factories.model.BirdFactory;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;
import cz.kea.api.model.Nest;
import cz.kea.api.model.Taxon;
import cz.kea.api.services.BirdService;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.dataproviders.NestDataProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = BirdsFormView.VIEW_NAME)
public class BirdsFormView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(BirdsView.class);

    public static final String VIEW_NAME = "birds-form";

    @Autowired
    private BirdService birdService;

    @Autowired
    private BirdFactory birdFactory;

    @Autowired
    private KeaMessageSource messageSource;

    @Autowired
    private NestDataProvider nestDataProvider;

    private Binder<Bird> binder;

    @PostConstruct
    public void init() {
        FormLayout formLayout = new FormLayout();
        addComponent(formLayout);

        ComboBox<Taxon> taxon = new ComboBox<>(messageSource.get("birds-taxon", getLocale()));
        formLayout.addComponent(taxon);

        ComboBox<Sex> sex = new ComboBox<>(messageSource.get("birds-sex", getLocale()));
        sex.setDataProvider(new ListDataProvider<Sex>(Arrays.asList(Sex.values())));
        sex.setItemCaptionGenerator(item -> messageSource.get(item, getLocale()));
        formLayout.addComponent(sex);

        ComboBox<State> state = new ComboBox<>(messageSource.get("birds-state", getLocale()));
        state.setDataProvider(new ListDataProvider<State>(Arrays.asList(State.values())));
        state.setItemCaptionGenerator(item -> messageSource.get(item, getLocale()));
        formLayout.addComponent(state);

        ComboBox<Nest> nest = new ComboBox<>(messageSource.get("nest", getLocale()));
        formLayout.addComponent(nest);

        DateField layed = new DateField(messageSource.get("birds-layed", getLocale()));
        formLayout.addComponent(layed);

        DateField hatched = new DateField(messageSource.get("birds-hatched", getLocale()));
        formLayout.addComponent(hatched);

        TextField mutation = new TextField(messageSource.get("birds-mutation", getLocale()));
        formLayout.addComponent(mutation);

        TextField identification = new TextField(messageSource.get("birds-identification", getLocale()));
        formLayout.addComponent(identification);

        TextField name = new TextField(messageSource.get("birds-name", getLocale()));
        formLayout.addComponent(name);

        ComboBox<Contact> owner = new ComboBox<>(messageSource.get("birds-contact", getLocale()));
        formLayout.addComponent(owner);

        TextArea note = new TextArea(messageSource.get("birds-note", getLocale()));
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
        Bird bird;

        if (StringUtils.isNotEmpty(event.getParameters())) {
            long id = Long.parseLong(event.getParameters());

            bird = birdService.findOne(id);
        } else {
            bird = birdFactory.createBird();
        }

        binder.setBean(bird);
    }

    private void save(Button.ClickEvent event) {
        try {
            Bird bird = binder.getBean();

            birdService.saveAndFlush(bird);

            getUI().getNavigator().navigateTo(BirdsView.VIEW_NAME);

            LOG.info("Saving of %s successful.", bird);
        } catch (Exception e) {
            LOG.error("Saving failed.", e);
            Notification.show(messageSource.get("saving-error", getLocale()), Notification.Type.ERROR_MESSAGE);
        } finally {
            event.getButton().setEnabled(true);
        }
    }

    private void cancel(Button.ClickEvent event) {
        getUI().getNavigator().navigateTo(BirdsView.VIEW_NAME);
    }
}
