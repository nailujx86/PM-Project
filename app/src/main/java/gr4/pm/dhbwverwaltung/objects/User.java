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

    public String getPasswhash() {
        return passwhash;
    }

    public void setPasswhash(String passwhash) {
        this.passwhash = passwhash;
    }

    public User(int id, String name, String email, String passwhash) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwhash = passwhash;
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
                users.add(new User(id, name, email, passw));
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
                arr.put(userobj);
            }
            obj.put("users", arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
}
