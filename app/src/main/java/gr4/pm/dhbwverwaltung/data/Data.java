package gr4.pm.dhbwverwaltung.data;

import gr4.pm.dhbwverwaltung.objects.User;

public class Data {
    private static Data instance;
    private Data(){};

    private User curUser = null;

    public static synchronized Data getInstance() {
        if(Data.instance == null) {
            Data.instance = new Data();
        }
        return Data.instance;
    }

    public User getUser() {
        return curUser;
    }

    public void setUser(User user) {
        curUser = user;
    }
}
