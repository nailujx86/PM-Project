package gr4.pm.dhbwverwaltung.auth;

import org.mindrot.jbcrypt.BCrypt;

public class Authentication {
    public static String hashpw(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkpw(String password, String hashpw) {
        return BCrypt.checkpw(password, hashpw);
    }
}
