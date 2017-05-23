package cz.kea.web.views.error;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@UIScope
@Component
public class ErrorView extends HorizontalLayout implements View {

    private static final Logger LOG = LogManager.getFormatterLogger(ErrorView.class);

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        LOG.trace("In error view.");
    }
}
