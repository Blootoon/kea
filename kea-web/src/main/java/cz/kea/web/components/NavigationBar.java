package cz.kea.web.components;

import javax.annotation.PostConstruct;

import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import cz.kea.web.views.BirdsView;
import cz.kea.web.views.DefaultView;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@UIScope
@Component
public class NavigationBar extends HorizontalLayout {

    private Button defaultView;
    private Button birdsView;

    @PostConstruct
    public void init() {
        defaultView = new Button("Default view");
        defaultView.addClickListener((e) -> getUI().getNavigator().navigateTo(DefaultView.VIEW_NAME));

        birdsView = new Button("Birds view");
        birdsView.addClickListener((e) -> getUI().getNavigator().navigateTo(BirdsView.VIEW_NAME));

        addComponent(defaultView);
        addComponent(birdsView);
    }
}
