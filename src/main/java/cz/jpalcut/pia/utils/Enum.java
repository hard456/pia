package cz.jpalcut.pia.utils;

/**
 * Obsahuje výčtové typy enum
 */
public class Enum {

    /**
     * Uživatelské role
     */
    public enum Role{
        ADMIN("ADMIN"),
        USER("USER");

        private String role;

        Role(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return role;
        }
    }

    /**
     * Typy požadavků na schválení adminem
     */
    public enum UserRequestType{
        LIMIT_BELOW("limit_below"),
        INTERNATIONAL_PAYMENT("international_payment");

        private String type;

        UserRequestType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

}
