package cz.kea.web.views.pair;

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
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.enums.State;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Pair;
import cz.kea.api.services.PairService;
import cz.kea.web.components.helpers.BirdHelper;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.components.dataproviders.BirdDataProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = PairFormView.VIEW_NAME)
public class PairFormView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(PairFormView.class);

    public static final String VIEW_NAME = "pair";

    @Autowired
    private PairService pairService;

    @Autowired
    private KeaMessageSource messageSource;

    @Autowired
    private BirdDataProvider birdDataProvider;

    @Autowired
    private BirdHelper birdHelper;

    private Binder<Pair> binder;

    private Pair bean;

    @PostConstruct
    public void init() {
        FormLayout formLayout = new FormLayout();
        addComponent(formLayout);

        ComboBox<Bird> male = new ComboBox<>(messageSource.get("male", getLocale()));
        male.setDataProvider(birdDataProvider, filter -> Collections.emptyMap());
        male.setItemCaptionGenerator(item -> birdHelper.descriptionGenerator(item));
        formLayout.addComponent(male);

        ComboBox<Bird> female = new ComboBox<>(messageSource.get("female", getLocale()));
        female.setDataProvider(birdDataProvider, filter -> Collections.emptyMap());
        female.setItemCaptionGenerator(item -> birdHelper.nameGenerator(item));
        formLayout.addComponent(female);

        ComboBox<State> state = new ComboBox<>(messageSource.get("state", getLocale()));
        state.setDataProvider(new ListDataProvider<State>(Arrays.asList(State.values())));
        state.setItemCaptionGenerator(item -> messageSource.get(item, getLocale()));
        formLayout.addComponent(state);

        DateField dateFrom = new DateField(messageSource.get("date-from", getLocale()));
        formLayout.addComponent(dateFrom);

        DateField dateTo = new DateField(messageSource.get("date-to", getLocale()));
        formLayout.addComponent(dateTo);

        TextArea note = new TextArea(messageSource.get("note", getLocale()));
        formLayout.addComponent(note);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button save = new Button(messageSource.get("save", getLocale()), e -> save(e));
        save.setDisableOnClick(true);
        buttonLayout.addComponent(save);

        Button cancel = new Button(messageSource.get("cancel", getLocale()), e -> cancel(e));
        buttonLayout.addComponent(cancel);

        formLayout.addComponent(buttonLayout);

        binder = new Binder<>(Pair.class);
        binder.bind(male, "male");
        binder.bind(female, "female");
        binder.bind(dateFrom, "dateFrom");
        binder.bind(dateTo, "dateTo");
        binder.bind(note, "note");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (StringUtils.isNotEmpty(event.getParameters())) {
            long id = Long.parseLong(event.getParameters());

            bean = pairService.findOne(id);
        } else {
            bean = pairService.create();
        }

        binder.readBean(bean);
    }

    private void save(Button.ClickEvent event) {
        try {
            binder.writeBeanIfValid(bean);

            pairService.saveAndFlush(bean);

            getUI().getNavigator().navigateTo(PairGridView.VIEW_NAME);

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
        getUI().getNavigator().navigateTo(PairGridView.VIEW_NAME);
    }
}
