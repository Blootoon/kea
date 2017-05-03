package cz.kea.web.components;

import javax.annotation.PostConstruct;

import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import cz.kea.web.views.birds.BirdsView;
import cz.kea.web.views.DefaultView;
import cz.kea.web.views.taxonomy.TaxonomyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
        addItem(messageSource.get("home-view", getLocale()), (e) -> getUI().getNavigator().navigateTo(DefaultView.VIEW_NAME));
        addItem(messageSource.get("birds-view", getLocale()), (e) -> getUI().getNavigator().navigateTo(BirdsView.VIEW_NAME));

        MenuItem dictionaries = addItem(messageSource.get("dictionaries", getLocale()), null);
        dictionaries.addItem(messageSource.get("taxonomy", getLocale()), (e) -> getUI().getNavigator().navigateTo(TaxonomyView.VIEW_NAME));
    }
}
