package cz.kea.api.services;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface PasswordEncryptionService {

    String encrypt(String password);
}
