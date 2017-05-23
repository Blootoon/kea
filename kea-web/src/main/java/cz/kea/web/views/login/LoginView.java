package cz.kea.web.views.login;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.VerticalLayout;
import cz.kea.api.exceptions.LoginException;
import cz.kea.api.model.User;
import cz.kea.api.services.UserService;
import cz.kea.web.components.helpers.DateTimeHelper;
import cz.kea.web.components.KeaMessageSource;
import cz.kea.web.views.DefaultView;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(LoginView.class);

    public static final String VIEW_NAME = "login";

    @Autowired
    private UserService userService;

    @Autowired
    private KeaMessageSource messageSource;

    @Autowired
    private DateTimeHelper dateTimeHelper;

    private LoginForm loginForm;

    private String redirectUrl;

    @PostConstruct
    public void init() {
        loginForm = new LoginForm();
        loginForm.setUsernameCaption(messageSource.get("email", getLocale()));
        loginForm.setPasswordCaption(messageSource.get("password", getLocale()));
        loginForm.setLoginButtonCaption(messageSource.get("login", getLocale()));
        loginForm.addLoginListener(e -> login(e.getLoginParameter("username"), e.getLoginParameter("password")));

        addComponent(loginForm);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        LOG.trace("In login view.");
        if (getSession().getAttribute(User.class) != null) {
            getUI().getNavigator().navigateTo(DefaultView.VIEW_NAME);
        } else {
            redirectUrl = viewChangeEvent.getParameters();
        }
    }

    private void login(String email, String password) {
        try {
            User user = userService.login(email, password);
            getSession().setAttribute(User.class, user);
            if (StringUtils.isEmpty(redirectUrl)) {
                getUI().getNavigator().navigateTo(DefaultView.VIEW_NAME);
            } else {
                getUI().getNavigator().navigateTo(redirectUrl);
            }
        } catch (LoginException e) {
            if (e.isLocked()) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(getLocale());
                getUI().setComponentError(new UserError(messageSource.get("account-is-locked", getLocale(), e.getLockReleaseTime().format(dateTimeFormatter))));
            } else {
                getUI().setComponentError(new UserError(messageSource.get("wrong-email-or-password", getLocale())));
            }
        }
    }
}
