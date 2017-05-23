package cz.kea.web;

import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import cz.kea.api.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@UIScope
@Component
public class KeaViewAccessControl implements ViewAccessControl {

    @Override
    public boolean isAccessGranted(UI ui, String beanName) {
        switch (beanName) {
            case "defaultView":
            case "loginView":
                return true;
            default:
                // disable auth for now
//                return ui.getSession().getAttribute(User.class) != null;
                return true;
        }
    }
}
