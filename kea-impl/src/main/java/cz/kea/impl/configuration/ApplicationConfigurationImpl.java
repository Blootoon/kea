package cz.kea.impl.configuration;

import cz.kea.api.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class ApplicationConfigurationImpl implements ApplicationConfiguration {

    @Value("${login.failed.attempts.max}")
    private int loginFailedAttemptsMax;

    @Value("${login.failed.attempts.timeout}")
    private int loginFailedAttemptsTimeout;

    public int getLoginFailedAttemptsMax() {
        return loginFailedAttemptsMax;
    }

    public void setLoginFailedAttemptsMax(int loginFailedAttemptsMax) {
        this.loginFailedAttemptsMax = loginFailedAttemptsMax;
    }

    public int getLoginFailedAttemptsTimeout() {
        return loginFailedAttemptsTimeout;
    }

    public void setLoginFailedAttemptsTimeout(int loginFailedAttemptsTimeout) {
        this.loginFailedAttemptsTimeout = loginFailedAttemptsTimeout;
    }
}
