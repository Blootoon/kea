package cz.kea.web.views.contact;

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
import cz.kea.api.model.Contact;
import cz.kea.api.services.ContactService;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.components.dataproviders.ContactDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = ContactGridView.VIEW_NAME)
public class ContactGridView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(ContactGridView.class);

    public static final String VIEW_NAME = "contacts";

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactDataProvider contactDataProvider;

    @Autowired
    private KeaMessageSource messageSource;

    private Button buttonNew;

    private Button buttonEdit;

    private Button buttonDelete;

    private Grid<Contact> grid;

    @PostConstruct
    public void init() {
        grid = new Grid<>();
        grid.setDataProvider(contactDataProvider);
        grid.addColumn(Contact::getId).setCaption(messageSource.get("id", getLocale()));
        grid.addColumn(Contact::getFirstName).setCaption(messageSource.get("first-name", getLocale()));
        grid.addColumn(Contact::getLastName).setCaption(messageSource.get("last-name", getLocale()));
        grid.addColumn(Contact::getEmail).setCaption(messageSource.get("email", getLocale()));
        grid.addColumn(Contact::getPhone).setCaption(messageSource.get("phone", getLocale()));
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

    private void onItemClick(Grid.ItemClick<Contact> event) {
        if (event.getMouseEventDetails().isDoubleClick()) {
            getUI().getNavigator().navigateTo(ContactFormView.VIEW_NAME + "/" + event.getItem().getId());
        }
    }

    private void onSelectionChange(SelectionEvent<Contact> event) {
        if (event.getAllSelectedItems().size() > 0) {
            buttonEdit.setEnabled(true);
            buttonDelete.setEnabled(true);
        } else {
            buttonEdit.setEnabled(false);
            buttonDelete.setEnabled(false);
        }
    }

    private void navigateToForm(Contact contact) {
        if (contact == null) {
            getUI().getNavigator().navigateTo(ContactFormView.VIEW_NAME);
        } else {
            getUI().getNavigator().navigateTo(ContactFormView.VIEW_NAME + "/" + contact.getId());
        }
    }

    private void editRecord() {
        Set<Contact> selected = grid.getSelectedItems();
        Iterator<Contact> iterator = selected.iterator();
        if (iterator.hasNext()) {
            navigateToForm(iterator.next());
        }
    }

    private void deleteRecord() {
        for (Contact contact : grid.getSelectedItems()) {
            contactService.delete(contact.getId());
        }
        grid.getDataProvider().refreshAll();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        LOG.trace("In contacts view.");
    }
}
