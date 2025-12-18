package srangeldev.centrococotero.models;

/**
 * Enum para los tipos de roles de usuario en el sistema
 */
public enum TipoRol {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String authority;

    TipoRol(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
