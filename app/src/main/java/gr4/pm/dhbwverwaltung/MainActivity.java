package gr4.pm.dhbwverwaltung;

import android.content.Intent;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import gr4.pm.dhbwverwaltung.auth.Authentication;
import gr4.pm.dhbwverwaltung.data.Data;
import gr4.pm.dhbwverwaltung.io.FileIO;
import gr4.pm.dhbwverwaltung.objects.Note;
import gr4.pm.dhbwverwaltung.objects.User;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDatabases();

        setContentView(R.layout.activity_login);

        /* /// TESTS ///
        User user1 = new User(123, "terst", "test@test.de", Authentication.hashpw("abc"));
        User user2 = new User(124, "test2", "test2@test.de", Authentication.hashpw("abdef"));
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Log.i("json", User.toJSON(users));
        ArrayList<User> users2 = User.parse(User.toJSON(users));
        Log.i("json2", User.toJSON(users2));
        Log.i("json3", Authentication.checkpw("abdef", user2.getPasswhash()) ? "true": "false");
        */
    }

    private void initDatabases() {
        if (!FileIO.fileExists("/dhbwverwaltung/users.json", false)) {
            ArrayList<User> users = new ArrayList<>();
            User user1 = new User(123, "test", "test@dhbw.de", Authentication.hashpw("test"));
            User user2 = new User(124, "julian", "email@email.de", Authentication.hashpw("passwort"));
            User user3 = new User(125, "moritz", "moritz@dhbw.de", Authentication.hashpw("pw"));
            users.add(user1);
            users.add(user2);
            users.add(user3);
            FileIO file = new FileIO("/dhbwverwaltung/users.json", false);
            file.writeToFile(User.toJSON(users));
            Log.i("dhbwverwaltung/userdb", "created");
        } else {
            Log.i("dhbwverwaltung/userdb", "exists");
        }
        if(!FileIO.fileExists("/dhbwverwaltung/notes.json", false)) {
            ArrayList<Note> notes = new ArrayList<>();
            Note note1 = new Note(1, 124, "Notiz: Julian");
            Note note2 = new Note(2, 124, "Eine etwas längere Notiz mit einem\nZeilenumbruch!");
            Note note3 = new Note(3, 125, "Eine Notiz");
            notes.add(note1);
            notes.add(note2);
            notes.add(note3);
            FileIO file = new FileIO("/dhbwverwaltung/notes.json", false);
            file.writeToFile(Note.toJSON(notes));
            Log.d("dhbwverwaltung/notedb", "initDatabases: " + Note.toJSON(notes));
            Log.d("dhbwverwaltung/notedb", "initDatabased: " + Note.toJSON(Note.parse(Note.toJSON(notes))));
            Log.i("dhbwverwaltung/notedb", "created");
        } else {
            Log.i("dhbwverwaltung/notedb", "exists");
        }
    }

    public void loginUser(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        EditText editTextUserName = (EditText) findViewById(R.id.editText2);
        String username = editTextUserName.getText().toString(); // speichert username in
        EditText editTextPassword = (EditText) findViewById(R.id.editText);
        String password = editTextPassword.getText().toString();

        FileIO db = new FileIO("/dhbwverwaltung/users.json", false);
        String jsondb = db.readFromFile();
        ArrayList<User> users = User.parse(jsondb);

        for(User user : users) {
            if(user.getEmail().equals(username)) {
                Log.i("dhbwverwaltung/login", "found user " + user.getEmail());
                Authentication.login(user, password);
                break;
            }
        }

        if(Data.getInstance().getUser() == null)
            return;

        startActivity(intent); //wechselt Ansicht zu activity_menu
        finish();
        overridePendingTransition(0,0);
    }
}
