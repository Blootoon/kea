package cz.kea.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface HierarchicalModel<ID extends Serializable> extends Identifiable<ID> {

    <T extends HierarchicalModel> T getParent();

    <T extends HierarchicalModel> void setParent(T parent);

    <T extends HierarchicalModel> List<T> getChildren();

    <T extends HierarchicalModel> void setChildren(List<T> children);
}
