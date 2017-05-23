package cz.kea.web.components.ui;

import javax.annotation.PostConstruct;

import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.MenuBar;
import cz.kea.api.model.User;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.views.bird.BirdGridView;
import cz.kea.web.views.DefaultView;
import cz.kea.web.views.contact.ContactGridView;
import cz.kea.web.views.login.LoginView;
import cz.kea.web.views.pair.PairGridView;
import cz.kea.web.views.taxon.TaxonTreeGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@UIScope
@Component
public class NavigationBar extends MenuBar {

    @Autowired
    private KeaMessageSource messageSource;

    @PostConstruct
    public void init() {
        addItem(messageSource.get("homepage", getLocale()), e -> getUI().getNavigator().navigateTo(DefaultView.VIEW_NAME));
        addItem(messageSource.get("birds", getLocale()), e -> getUI().getNavigator().navigateTo(BirdGridView.VIEW_NAME));
        addItem(messageSource.get("pairs", getLocale()), e -> getUI().getNavigator().navigateTo(PairGridView.VIEW_NAME));

        MenuItem dictionaries = addItem(messageSource.get("dictionaries", getLocale()), null);
        dictionaries.addItem(messageSource.get("contacts", getLocale()), e -> getUI().getNavigator().navigateTo(ContactGridView.VIEW_NAME));
        dictionaries.addItem(messageSource.get("taxonomy", getLocale()), e -> getUI().getNavigator().navigateTo(TaxonTreeGridView.VIEW_NAME));

//        User user = getSession().getAttribute(User.class);
//        if (user != null) {
//            addUserMenu(user);
//        }
    }

    /**
     * Creates user's menu item.
     * @param user
     * @return
     */
    private void addUserMenu(User user) {
        MenuItem userMenu = addItem(user.getFullName(), null);
        userMenu.addItem(messageSource.get("account", getLocale()), null);
        userMenu.addSeparator();
        userMenu.addItem(messageSource.get("logout", getLocale()), e -> logout());
    }

    /**
     * Handles logout action.
     */
    private void logout() {
        // close session
        getSession().close();
        // and navigate to login view
        getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
    }
}
