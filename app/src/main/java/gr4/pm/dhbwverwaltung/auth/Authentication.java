package gr4.pm.dhbwverwaltung.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Authentication {

    /**
     * Hasht das Passwort mit dem Blowfish-Algorithmus
     *
     * @param password
     * @return hashed password
     */
    public static String hashpw(String password) {
        return BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(12, password.toCharArray());
    }

    /**
     * Vergleicht ein Passwort das im Klartext vorliegt
     * mit dem Hashwert eines Passworts
     *
     * @param password
     * @param hashpw
     * @return boolean
     */
    public static boolean checkpw(String password, String hashpw) {
        return BCrypt.verifyer().verify(password.toCharArray(), hashpw.toCharArray()).verified;
    }
}
