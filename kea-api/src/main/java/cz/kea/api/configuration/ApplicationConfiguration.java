package cz.kea.api.configuration;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface ApplicationConfiguration {

    int getLoginFailedAttemptsMax();

    int getLoginFailedAttemptsTimeout();
}
