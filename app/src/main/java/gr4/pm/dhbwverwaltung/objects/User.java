package gr4.pm.dhbwverwaltung.objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private String passwhash;
    private Boolean stayLoggedIn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswhash() { return passwhash; }

    public void setPasswhash(String passwhash) { this.passwhash = passwhash;  }

    public Boolean getStayLoggedIn() { return stayLoggedIn; }

    public void setStayLoggedIn(Boolean stayLoggedIn) { this.stayLoggedIn = stayLoggedIn;  }

    public User(int id, String name, String email, String passwhash, Boolean stayLoggedIn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwhash = passwhash;
        this.stayLoggedIn = stayLoggedIn;
    }

    /**
     * Wandelt einen json String in einem bestimmten Format in eine ArrayList von Usern um
     *
     * @param json JSON String der geparsed werden soll
     * @return ArrayList des Typs User
     */
    public static ArrayList<User> parse(String json) {
        ArrayList<User> users = new ArrayList<>();
        JSONArray userArr = null;
        try {
            JSONObject obj = new JSONObject(json);
            userArr = obj.getJSONArray("users");
            for (int i = 0; i < userArr.length(); i++) {
                JSONObject userDetail = userArr.getJSONObject(i);
                String name = userDetail.getString("name");
                String email = userDetail.getString("email");
                int id = userDetail.getInt("id");
                String passw = userDetail.getString("passwhash");
                Boolean stayLoggedIn = userDetail.getBoolean("stayLoggedIn");
                users.add(new User(id, name, email, passw, stayLoggedIn));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Wandelt eine ArrayList von Usern in einen JSON String um
     *
     * @param users ArrayList des Typs User
     * @return JSON String
     */
    public static String toJSON(ArrayList<User> users) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        try {
            for (User user : users) {
                JSONObject userobj = new JSONObject();
                userobj.put("name", user.getName());
                userobj.put("email", user.getEmail());
                userobj.put("id", user.getId());
                userobj.put("passwhash", user.getPasswhash());
                userobj.put("StayLoggedIn",user.getStayLoggedIn());
                arr.put(userobj);
            }
            obj.put("users", arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
}
