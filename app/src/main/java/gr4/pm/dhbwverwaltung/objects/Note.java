package gr4.pm.dhbwverwaltung.objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Note {
    private int id;
    private int idUser;
    private String contents;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Note(int id, int idUser, String contents) {
        this.id = id;
        this.idUser = idUser;
        this.contents = contents;
    }

    /**
     * Wandelt einen json String in einem bestimmten Format in eine ArrayList von Notizen um
     *
     * @param json JSON String der geparsed werden soll
     * @return ArrayList des Typs Note
     */
    public static ArrayList<Note> parse(String json) {
        ArrayList<Note> notes = new ArrayList<>();
        JSONArray noteArr = null;
        try {
            JSONObject obj = new JSONObject(json);
            noteArr = obj.getJSONArray("notes");
            for (int i = 0; i < noteArr.length(); i++) {
                JSONObject noteDetail = noteArr.getJSONObject(i);
                String contents = noteDetail.getString("contents");
                int userId = noteDetail.getInt("userId");
                int id = noteDetail.getInt("id");
                notes.add(new Note(id, userId, contents));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return notes;
    }

    /**
     * Wandelt eine ArrayList von Notes in einen JSON String um
     *
     * @param notes ArrayList des Typs Note
     * @return JSON String
     */
    public static String toJSON(ArrayList<Note> notes) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        try {
            for (Note note : notes) {
                JSONObject noteobj = new JSONObject();
                noteobj.put("id", note.getId());
                noteobj.put("idUser", note.getIdUser());
                noteobj.put("contents", note.getContents());
                arr.put(noteobj);
            }
            obj.put("notes", arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
}
