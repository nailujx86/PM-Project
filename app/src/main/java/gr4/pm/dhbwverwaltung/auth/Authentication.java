package gr4.pm.dhbwverwaltung.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Authentication {
    public static String hashpw(String password) {
        return BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(12, password.toCharArray());
    }

    public static boolean checkpw(String password, String hashpw) {
        return BCrypt.verifyer().verify(password.toCharArray(), hashpw.toCharArray()).verified;
    }
}
