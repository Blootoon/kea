package cz.kea.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import cz.kea.web.components.NavigationBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@SpringUI
@SpringViewDisplay
public class KeaUI extends UI implements ViewDisplay {

    @Autowired
    private NavigationBar navigationBar;

    @Autowired
    private SpringViewProvider springViewProvider;

    private VerticalLayout root;
    private Panel viewContainer;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        viewContainer = new Panel();

        root = new VerticalLayout();
        root.addComponent(navigationBar);
        root.addComponent(viewContainer);

        setContent(root);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(springViewProvider);
    }

    @Override
    public void showView(View view) {
        viewContainer.setContent((Component) view);
    }

    @WebListener
    public static class KeaContextLoaderListener extends ContextLoaderListener {
    }

    @Configuration
    @EnableVaadin
    public static class KeaConfiguration {
    }

    @WebServlet(urlPatterns = "/*", name = "KeaUIServlet", asyncSupported = true)
    public static class KeaUIServlet extends SpringVaadinServlet {

    }
}
