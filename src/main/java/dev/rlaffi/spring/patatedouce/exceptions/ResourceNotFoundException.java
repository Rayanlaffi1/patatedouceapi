package dev.rlaffi.spring.patatedouce.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(Class<?> resourceType, Object resourceId) {
        super(resourceType.getSimpleName() + " " + resourceId + " not found");
    }
}
