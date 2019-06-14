package gr4.pm.dhbwverwaltung.auth;

import android.util.Log;

import gr4.pm.dhbwverwaltung.data.Data;
import gr4.pm.dhbwverwaltung.objects.User;

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

    public static User login(User user, String passw) {
        Data data = Data.getInstance();
        if(checkpw(passw, user.getPasswhash())) {
            Log.i("dhbwverwaltung/login", user.getEmail() + " logged in");
            data.setUser(user);
            return user;
        }
        return null;
    }
}
