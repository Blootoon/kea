package cz.kea.impl.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.FilterJoinTables;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Entity
@Table(name = "CONTACT")
public class ContactEntity implements Contact {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @OneToMany(targetEntity = BirdEntity.class, mappedBy = "owner")
    private List<Bird> birds;

    @Lob
    @Column(name = "NOTE")
    private String note;

    public ContactEntity() {
    }

    public ContactEntity(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public ContactEntity(String firstName, String lastName, String email, String phone, List<Bird> birds, String note) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birds = birds;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Bird> getBirds() {
        return birds;
    }

    public void setBirds(List<Bird> birds) {
        this.birds = birds;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getFullName() {
        return StringUtils.join(new String[]{firstName, lastName}, StringUtils.SPACE).trim();
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            '}';
    }
}
