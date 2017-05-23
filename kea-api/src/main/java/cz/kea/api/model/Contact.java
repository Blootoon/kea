package cz.kea.api.model;

import java.util.List;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface Contact extends Identifiable<Long> {

    void setId(Long id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPhone();

    void setPhone(String phone);

    List<Bird> getBirds();

    void setBirds(List<Bird> birds);

    String getNote();

    void setNote(String note);

    String getFullName();
}
