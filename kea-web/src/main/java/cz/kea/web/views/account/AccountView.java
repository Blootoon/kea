package cz.kea.web.views.account;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.model.User;
import cz.kea.web.views.login.LoginView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = AccountView.VIEW_NAME)
public class AccountView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(AccountView.class);

    public static final String VIEW_NAME = "account";

    @PostConstruct
    public void init() {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        User user = getSession().getAttribute(User.class);
        if (user == null) {
            getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
        } else {

        }
    }
}
