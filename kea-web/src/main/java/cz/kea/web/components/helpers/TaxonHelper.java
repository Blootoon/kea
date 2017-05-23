package cz.kea.web.components.helpers;

import cz.kea.api.model.Taxon;
import liquibase.util.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class TaxonHelper {

    public String nameGenerator(Taxon taxon) {
        if (taxon == null) {
            return null;
        } else {
            return taxon.getCzechName();
        }
    }

    public String descriptionGenerator(Taxon taxon) {
        if (taxon == null) {
            return null;
        } else {
            return StringUtils.escapeHtml(taxon.getCzechName()) + " <i>(" + StringUtils.escapeHtml(taxon.getLatinName()) + ")</i>";
        }
    }
}
