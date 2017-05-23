package cz.kea.web.components.helpers;

import cz.kea.api.model.Bird;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class BirdHelper {

    @Autowired
    private TaxonHelper taxonHelper;

    @Autowired
    private SexHelper sexHelper;

    public String nameGenerator(Bird bird) {
        if (bird == null) {
            return null;
        } else {
            return StringUtils.join(new String[]{bird.getName(), bird.getIdentification()}, ", ").trim();
        }
    }

    public String descriptionGenerator(Bird bird) {
        if (bird == null) {
            return null;
        } else {
            String taxon = taxonHelper.nameGenerator(bird.getTaxon());
            String name = sexHelper.symbolGenerator(bird.getSex()) + " " + nameGenerator(bird);
            if (StringUtils.isEmpty(taxon)) {
                return name;
            } else {
                return name  + " (" + taxon + ")";
            }
        }
    }
}
