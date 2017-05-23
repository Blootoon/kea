package cz.kea.impl.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cz.kea.api.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Entity
@Table(name = "USERS")
public class UserEntity implements User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Column(name = "LAST_LOGIN_ATTEMPT")
    private LocalDateTime lastLoginAttempt;

    @Column(name = "FAILED_ATTEMPTS", nullable = false)
    private int failedAttempts;

    @Column(name = "LOCKED", nullable = false)
    private boolean locked;

    public UserEntity() {
        this(null, null, null, null);
    }

    public UserEntity(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.failedAttempts = 0;
        this.locked = false;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    @Override
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public LocalDateTime getLastLoginAttempt() {
        return lastLoginAttempt;
    }

    @Override
    public void setLastLoginAttempt(LocalDateTime lastLoginAttempt) {
        this.lastLoginAttempt = lastLoginAttempt;
    }

    @Override
    public int getFailedAttempts() {
        return failedAttempts;
    }

    @Override
    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String getFullName() {
        return StringUtils.join(new String[]{firstName, lastName}, StringUtils.SPACE).trim();
    }

    @Override
    public String toString() {
        return "UserEntity{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
