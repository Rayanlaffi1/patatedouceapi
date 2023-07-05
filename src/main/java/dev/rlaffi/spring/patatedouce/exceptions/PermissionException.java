package dev.rlaffi.spring.patatedouce.exceptions;

public class PermissionException extends Exception {
    public PermissionException(Object resourceId) {
        super("Vous n'avez pas la permission pour " + resourceId);
    }
}
