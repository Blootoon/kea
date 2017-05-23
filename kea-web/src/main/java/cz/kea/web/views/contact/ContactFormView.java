package cz.kea.web.views.contact;

import javax.annotation.PostConstruct;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.model.Contact;
import cz.kea.api.services.ContactService;
import cz.kea.web.components.KeaMessageSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = ContactFormView.VIEW_NAME)
public class ContactFormView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(ContactFormView.class);

    public static final String VIEW_NAME = "contact";

    @Autowired
    private ContactService contactService;

    @Autowired
    private KeaMessageSource messageSource;

    private Binder<Contact> binder;

    private Contact bean;

    @PostConstruct
    public void init() {
        FormLayout formLayout = new FormLayout();
        addComponent(formLayout);

        TextField firstName = new TextField(messageSource.get("first-name", getLocale()));
        formLayout.addComponent(firstName);

        TextField lastName = new TextField(messageSource.get("last-name", getLocale()));
        formLayout.addComponent(lastName);

        TextField email = new TextField(messageSource.get("email", getLocale()));
        formLayout.addComponent(email);

        TextField phone = new TextField(messageSource.get("phone", getLocale()));
        formLayout.addComponent(phone);

        TextArea note = new TextArea(messageSource.get("note", getLocale()));
        formLayout.addComponent(note);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button save = new Button(messageSource.get("save", getLocale()), e -> save(e));
        save.setDisableOnClick(true);
        buttonLayout.addComponent(save);

        Button cancel = new Button(messageSource.get("cancel", getLocale()), e -> cancel(e));
        buttonLayout.addComponent(cancel);

        formLayout.addComponent(buttonLayout);

        binder = new Binder<>(Contact.class);
        binder.bind(firstName, "firstName");
        binder.bind(lastName, "lastName");
        binder.bind(email, "email");
        binder.bind(phone, "phone");
        binder.bind(note, "note");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (StringUtils.isNotEmpty(event.getParameters())) {
            long id = Long.parseLong(event.getParameters());

            bean = contactService.findOne(id);
        } else {
            bean = contactService.create();
        }

        binder.readBean(bean);
    }

    private void save(Button.ClickEvent event) {
        try {
            binder.writeBeanIfValid(bean);

            contactService.saveAndFlush(bean);

            getUI().getNavigator().navigateTo(ContactGridView.VIEW_NAME);

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
        getUI().getNavigator().navigateTo(ContactGridView.VIEW_NAME);
    }
}
