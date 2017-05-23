package cz.kea.api.model;

import java.time.LocalDateTime;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface User extends Identifiable<Long> {

    void setId(Long id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPhone();

    void setPhone(String phone);

    String getPassword();

    void setPassword(String password);

    LocalDateTime getLastLogin();

    void setLastLogin(LocalDateTime lastLogin);

    LocalDateTime getLastLoginAttempt();

    void setLastLoginAttempt(LocalDateTime lastLoginAttempt);

    int getFailedAttempts();

    void setFailedAttempts(int failedAttempts);

    boolean isLocked();

    void setLocked(boolean locked);

    String getFullName();
}
