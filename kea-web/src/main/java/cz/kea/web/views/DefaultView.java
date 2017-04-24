package cz.kea.web.views;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {

    private static final Logger LOG = Logger.getLogger(DefaultView.class);

    public static final String VIEW_NAME = "";

    @PostConstruct
    public void init() {
        addComponent(new Label("Default view"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LogMF.trace(LOG, "In default view.", new Object[]{});
    }
}
