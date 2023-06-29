package dev.rlaffi.spring.patatedouce.exceptions;

public class IntegrityViolationException extends Exception {
    public IntegrityViolationException(Class<?> resourceType) {
        super("Erreur d'intégrité - " + resourceType.getSimpleName());
    }
}
